package net.malvanav.service;

import net.malvanav.dto.EmpNamesDto;
import net.malvanav.dto.FilterRequest;
import net.malvanav.entity.Employee;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
  Employee save(Employee employee);

  List<Employee> findAll();

  Optional<Employee> findById(Long id);

  void delete(Long id);

  Employee update(Long id, Employee employee);

  Page<Employee> getPaginatedEmployees(FilterRequest filterRequest);

  List<EmpNamesDto> getAllEmployeeName();
}
