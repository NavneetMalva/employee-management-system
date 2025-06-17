package net.malvanav.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import net.malvanav.enums.EmployeeErrorCodes;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException {
  private final HttpStatus httpStatus;
  private final String errorDescription;
  private final EmployeeErrorCodes employeeErrorCodes;

  /**
   * Constructor for Base Exception
   * @param httpStatus
   * @param errorDescription
   * @param employeeErrorCodes
   */
  public BaseException(HttpStatus httpStatus, String errorDescription, EmployeeErrorCodes employeeErrorCodes) {
    this.httpStatus = httpStatus;
    this.errorDescription = errorDescription;
    this.employeeErrorCodes = employeeErrorCodes;
  }

}
