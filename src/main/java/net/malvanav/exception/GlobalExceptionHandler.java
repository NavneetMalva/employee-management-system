package net.malvanav.exception;

import net.malvanav.domain.ResponseDomain;
import net.malvanav.enums.EmployeeErrorCodes;
import net.malvanav.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<ResponseDomain> handleException(Exception ex){
    if(ex instanceof ApplicationException applicationException){
      return new ResponseEntity<>(ResponseUtil.populateErrorResponseDomain(applicationException.getHttpStatus(), applicationException.getErrorDescription(), applicationException.getEmployeeErrorCodes()),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(ResponseUtil.populateErrorResponseDomain(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), EmployeeErrorCodes.INTERNAL_SYSTEM_ERROR),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
