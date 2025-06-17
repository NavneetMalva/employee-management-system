package net.malvanav.util;

import net.malvanav.entity.Employee;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification {

  public static Specification<Employee> hasName(String name) {
    return (root, query, criteriaBuilder) -> {
      if (name == null || name.isEmpty()) {
        return null;
      }
      return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    };
  }

  public static Specification<Employee> hasDepartment(String department) {
    return (root, query, cb) -> {
      if (department == null || department.isBlank()) return null;
      return cb.like(cb.lower(root.get("department")), "%" + department.toLowerCase() + "%");
    };
  }

}
