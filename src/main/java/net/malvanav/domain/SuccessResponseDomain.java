package net.malvanav.domain;

import lombok.Data;

import java.util.List;

@Data
public class SuccessResponseDomain<T> {
    private T data;
    private List<T> dataList;
    private String responseMessage;
}
