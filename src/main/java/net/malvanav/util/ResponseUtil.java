package net.malvanav.util;

import net.malvanav.domain.ErrorResponseDomain;
import net.malvanav.domain.ResponseDomain;
import net.malvanav.domain.SuccessResponseDomain;
import net.malvanav.enums.EmployeeErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

public class ResponseUtil<T> {

  /**
   * populate success response with a message only
   *
   * @return
   */
  public static <T> ResponseDomain populateSuccessResponseDomain(String message) {
    ResponseDomain responseDomain = new ResponseDomain();
    SuccessResponseDomain<T> successResponseDomain = new SuccessResponseDomain<T>();
    successResponseDomain.setResponseMessage(message);
    responseDomain.setSuccess(successResponseDomain);
    return responseDomain;
  }

  /**
   * populate success response with data
   *
   * @param data
   * @return
   */
  public static <T> ResponseDomain populateSuccessResponseDomain(T data) {
    ResponseDomain responseDomain = new ResponseDomain();
    SuccessResponseDomain<T> successResponseDomain = new SuccessResponseDomain<T>();
    successResponseDomain.setData(data);
    responseDomain.setSuccess(successResponseDomain);
    return responseDomain;
  }

  /**
   * populate success response with data and message
   *
   * @param data
   * @param message
   * @return
   */
  public static <T> ResponseDomain populateSuccessResponseDomain(T data, String message) {
    ResponseDomain responseDomain = new ResponseDomain();
    SuccessResponseDomain<T> successResponseDomain = new SuccessResponseDomain<T>();
    successResponseDomain.setData(data);
    successResponseDomain.setResponseMessage(message);
    responseDomain.setSuccess(successResponseDomain);
    return responseDomain;
  }

  /**
   * populate success response with List of data and message
   *
   * @param dataList
   * @param message
   * @return
   */
  public static <T> ResponseDomain populateSuccessResponseDomain(List<T> dataList, String message) {
    ResponseDomain responseDomain = new ResponseDomain();
    SuccessResponseDomain<T> successResponseDomain = new SuccessResponseDomain<T>();
    successResponseDomain.setDataList(dataList);
    successResponseDomain.setResponseMessage(message);
    responseDomain.setSuccess(successResponseDomain);
    return responseDomain;
  }

  /**
   * populate error response with data and errorMessage
   *
   * @param httpStatus
   * @param errorMessage
   * @return
   */
  public static <T> ResponseDomain populateErrorResponseDomain(HttpStatus httpStatus, String errorMessage) {
    ResponseDomain responseDomain = new ResponseDomain();
    ErrorResponseDomain errorResponseDomain = new ErrorResponseDomain();
    errorResponseDomain.setErrorMessage(errorMessage);
    errorResponseDomain.setStatusCodeMessage(httpStatus);
    errorResponseDomain.setStatusCode(httpStatus.value());
    responseDomain.setError(errorResponseDomain);
    return responseDomain;
  }


  /**
   * Creates a {@link ResponseEntity} containing an error response domain populated with the given HTTP status,
   * error message, and optional custom employee error codes.
   *
   * @param httpStatus   the HTTP status to set in the error response
   * @param errorMessage the error message describing the error
   * @param errorCodes   an optional {@link EmployeeErrorCodes} enum containing custom error code details; may be null
   * @return a {@link ResponseEntity} containing a {@link ResponseDomain} with the error details set
   */
  public static ResponseEntity<Object> populateErrorResponseDomain(HttpStatus httpStatus, String errorMessage, EmployeeErrorCodes errorCodes) {
    ResponseDomain responseDomain = new ResponseDomain();
    ErrorResponseDomain errorResponseDomain = new ErrorResponseDomain();
    errorResponseDomain.setStatusCode(httpStatus.value());
    errorResponseDomain.setStatusCodeMessage(httpStatus);
    errorResponseDomain.setErrorMessage(errorMessage);
    if (Objects.nonNull(errorCodes)) {
      errorResponseDomain.setErrorCode(errorCodes.getCode());
      errorResponseDomain.setErrorDescription(errorCodes.getDescription());
      errorResponseDomain.setErrorTitle(errorCodes.getTitle());
    }
    responseDomain.setError(errorResponseDomain);
    return new ResponseEntity<>(responseDomain, responseDomain.getError().getStatusCodeMessage()) ;
  }

}
