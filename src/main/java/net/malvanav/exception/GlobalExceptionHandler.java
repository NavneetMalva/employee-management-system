package net.malvanav.exception;

import net.malvanav.enums.EmployeeErrorCodes;
import net.malvanav.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<Object> handleException(Exception ex) {
    if (ex instanceof ApplicationException applicationException) {
      return ResponseUtil.populateErrorResponseDomain(applicationException.getHttpStatus(), applicationException.getErrorDescription(), applicationException.getEmployeeErrorCodes());
    }
    return ResponseUtil.populateErrorResponseDomain(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), EmployeeErrorCodes.INTERNAL_SYSTEM_ERROR);
  }

}
