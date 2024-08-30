package com.example.sourcebase.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import javax.tools.Diagnostic;

@Getter
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    USER_NOT_FOUND(404, "User Not Found", HttpStatus.NOT_FOUND),
    ROLE_NOT_FOUND(404, "Role Not Found", HttpStatus.NOT_FOUND),
    USER_NOT_EXISTS(409, "User Not Exists", HttpStatus.CONFLICT),
    ID_NOT_EXISTS(409, "Id Not Exists", HttpStatus.CONFLICT),
<<<<<<< HEAD
    ILLEGAL_STATE(400, "Wrong password or username", HttpStatus.BAD_REQUEST),
    ILLEGAL_ARGUMENT(409, "Email, Phone or Username Already Exists", HttpStatus.CONFLICT),
    VALIDATION_ERROR(400, "", HttpStatus.BAD_REQUEST),
<<<<<<< HEAD
    CREATED(201, "Success Created" , HttpStatus.CREATED),;
=======
    USERNAME_EXISTS(409, "Username Exists", HttpStatus.CONFLICT),
    ILLEGAL_STATE(400, "Wrong password or username", HttpStatus.BAD_REQUEST),
    ILLEGAL_ARGUMENT(409, "Email, Phone or Username Already Exists", HttpStatus.CONFLICT),
    VALIDATION_ERROR(400, "", HttpStatus.BAD_REQUEST)
    ;
>>>>>>> e96da83 (update source base)
=======
    CREATED(201, "Success Created" , HttpStatus.CREATED),
    DELETE_SUCCESSFUL(1012, "Delete successful", HttpStatus.OK),
    UPDATE_SUCCESSFUL(1013, "Update successful", HttpStatus.OK),
    GET_SUCCESSFUL(1010, "Get successful", HttpStatus.OK),;
>>>>>>> 29181f91eab65d5ca609d2bff852822a0eb8f31c
    int code;
    String message;
    HttpStatus httpStatus;
}
