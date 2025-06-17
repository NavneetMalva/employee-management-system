package net.malvanav.service.impl;

import lombok.RequiredArgsConstructor;
import net.malvanav.dto.EmpNamesDto;
import net.malvanav.dto.FilterRequest;
import net.malvanav.enums.EmployeeErrorCodes;
import net.malvanav.exception.ApplicationException;
import net.malvanav.entity.Employee;
import net.malvanav.repository.EmployeeRepository;
import net.malvanav.service.EmployeeService;
import net.malvanav.util.EmployeeSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository repository;

  @Override
  public Employee save(Employee employee) {
    return repository.save(employee);
  }

  @Override
  public List<Employee> findAll() {
    return repository.findAll();
  }

  @Override
  public Optional<Employee> findById(Long id) {
    return repository.findById(id);
  }

  @Override
  public void delete(Long id) {
    if (!repository.existsById(id)) {
      throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, "Employee not found with id " + id, EmployeeErrorCodes.EMPLOYEE_NOT_FOUND);
    }
    repository.deleteById(id);
  }

  @Override
  public Employee update(Long id, Employee employee) {
    Employee existingEmp = repository.findById(id)
        .orElseThrow(() -> new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, "Employee not found with id " + id, EmployeeErrorCodes.EMPLOYEE_NOT_FOUND));

    // Update only the fields that are allowed to change
    existingEmp.setName(employee.getName());
    existingEmp.setEmail(employee.getEmail());
    existingEmp.setDepartment(employee.getDepartment());

    return repository.save(existingEmp);
  }

  @Override
  public Page<Employee> getPaginatedEmployees(FilterRequest filterRequest) {
    try {
      String sortDir = filterRequest.getSortDir();
      Sort.Direction direction = sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
      String sortBy = filterRequest.getSortBy();
      Sort sort = Sort.by(direction, sortBy);
      Pageable pageable = PageRequest.of(filterRequest.getPage(), filterRequest.getPageSize(), sort);

      // Build dynamic specification based on available fields
      Specification<Employee> spec = Specification.where(null); // start with empty

      if (filterRequest.getName() != null && !filterRequest.getName().isBlank()) {
        spec = spec.and(EmployeeSpecification.hasName(filterRequest.getName()));
      }
      if (filterRequest.getDepartment() != null && !filterRequest.getDepartment().isBlank()) {
        spec = spec.and(EmployeeSpecification.hasDepartment(filterRequest.getDepartment()));
      }

      return repository.findAll(spec, pageable);
    } catch (Exception e) {
      throw new ApplicationException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to retrieved the Employees.", EmployeeErrorCodes.INTERNAL_SYSTEM_ERROR);
    }
  }

  @Override
  public List<EmpNamesDto> getAllEmployeeName(){
    List<Employee> allEmployee = repository.findAll();
    return allEmployee.stream().map(emp -> new EmpNamesDto(emp.getName(), emp.getDepartment()))
        .toList();
  }

}
