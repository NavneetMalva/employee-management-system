package net.malvanav.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDomain {
    private HttpStatus statusCodeMessage;
    private Integer statusCode;
    private String timestamp;
    private String errorCode;
    private String errorDescription;
    private String errorTitle;
    private String errorMessage;
}
