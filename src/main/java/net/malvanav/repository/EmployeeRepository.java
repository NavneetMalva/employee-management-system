package net.malvanav.repository;

import net.malvanav.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  Page<Employee> findAll(Specification<Employee> spec, Pageable pageable);
}
