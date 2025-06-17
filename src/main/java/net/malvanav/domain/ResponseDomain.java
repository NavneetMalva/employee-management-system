package net.malvanav.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDomain {
    private SuccessResponseDomain<?> success;
    private ErrorResponseDomain error;
}
