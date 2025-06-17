package net.malvanav.exception;

import net.malvanav.enums.EmployeeErrorCodes;
import org.springframework.http.HttpStatus;

public class ApplicationException extends BaseException{

  public ApplicationException(HttpStatus httpStatus, String errorDescription, EmployeeErrorCodes employeeErrorCodes){
    super(httpStatus, errorDescription, employeeErrorCodes);
  }
}
