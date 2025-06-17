package net.malvanav.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.malvanav.dto.EmpNamesDto;
import net.malvanav.dto.FilterRequest;
import net.malvanav.service.EmployeeService;
import net.malvanav.domain.ResponseDomain;
import net.malvanav.entity.Employee;
import net.malvanav.util.ResponseUtil;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

  private final EmployeeService service;

  @PostMapping
  public ResponseEntity<ResponseDomain> create(@Valid @RequestBody Employee employee) {
    Employee savedEmp = service.save(employee);
    return new ResponseEntity<>(ResponseUtil.populateSuccessResponseDomain(savedEmp, "Employee saved successfully."), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<ResponseDomain> getAll() {
    List<Employee> list = service.findAll();
    return new ResponseEntity<>(ResponseUtil.populateSuccessResponseDomain(list, "List of employees retried successfully."), HttpStatus.OK);
  }


  @GetMapping("/{id}")
  public ResponseEntity<ResponseDomain> getById(@PathVariable Long id) {
    return service.findById(id)
        .map(emp -> new ResponseEntity<>(ResponseUtil.populateSuccessResponseDomain(emp, "Employee retrieved successfully."), HttpStatus.OK))
        .orElse(new ResponseEntity<>(ResponseUtil.populateSuccessResponseDomain("Employee not found with id : " + id), HttpStatus.OK));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDomain> delete(@PathVariable Long id) {
    service.delete(id);
    return new ResponseEntity<>(ResponseUtil.populateSuccessResponseDomain("Employee deleted successfully."), HttpStatus.OK);
  }

  @PostMapping("/update/{id}")
  public ResponseEntity<ResponseDomain> updateById(@PathVariable Long id, @Valid @RequestBody Employee employee) {
    Employee updateEmp = service.update(id, employee);
    return new ResponseEntity<>(ResponseUtil.populateSuccessResponseDomain(updateEmp, "Employee updated successfully for id : " + id), HttpStatus.OK);

  }

  @PostMapping("/dashboard")
  public ResponseEntity<ResponseDomain> dashboard(@Valid @RequestBody FilterRequest filterRequest) {
    Page<Employee> paginatedEmployees = service.getPaginatedEmployees(filterRequest);
    if (paginatedEmployees != null) {
      return new ResponseEntity<>(ResponseUtil.populateSuccessResponseDomain(paginatedEmployees, "Employees retrieved successfully."), HttpStatus.OK);
    }
    return new ResponseEntity<>(ResponseUtil.populateSuccessResponseDomain("No Employees found."),HttpStatus.OK);
  }

  @GetMapping("/dashboard/names")
  public ResponseEntity<ResponseDomain> getAllNames(){
    List<EmpNamesDto> allEmployeeName = service.getAllEmployeeName();
    return new ResponseEntity<>(ResponseUtil.populateSuccessResponseDomain(allEmployeeName, "List of all employees retrieved successfully."), HttpStatus.OK);
  }
}
