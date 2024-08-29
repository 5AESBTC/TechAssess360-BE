package com.example.sourcebase.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ResponseData<T> {
    int code;
    String message;
    String status;
    T data;
    Date timestamp;
    String path;
    String error;

//    public ResponseData(ErrorCode errorCode, Object data) {
//        this.code = errorCode.getCode();
//        this.message = errorCode.getMessage();
//        this.status = errorCode.getHttpStatus().toString();
//        this.data = data;
//    }
//
//    public ResponseData(ErrorCode errorCode) {
//        this.code = errorCode.getCode();
//        this.message = errorCode.getMessage();
//        this.status = errorCode.getHttpStatus().toString();
//        this.data = null;
//    }
}
