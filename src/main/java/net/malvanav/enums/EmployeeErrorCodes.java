package net.malvanav.enums;

import lombok.Getter;

@Getter
public enum EmployeeErrorCodes {

  INTERNAL_SYSTEM_ERROR("EMS101", "Internal system error occurred due to while processing the request", "Internal system Error"),
  EMPLOYEE_NOT_FOUND("EMS102", "Employee not found", "Employee Not Found");

  private final String code;
  private final String description;
  private final String title;

  EmployeeErrorCodes(String code, String description, String title) {
    this.code = code;
    this.description = description;
    this.title = title;
  }

}
