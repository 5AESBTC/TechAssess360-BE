package com.example.sourcebase.controller;

import com.example.sourcebase.domain.User;
import com.example.sourcebase.domain.dto.reqdto.user.RegisterReqDTO;
import com.example.sourcebase.domain.dto.resdto.user.UserResDTO;
import com.example.sourcebase.service.IUserService;
import com.example.sourcebase.util.ErrorCode;
import com.example.sourcebase.util.ResponseData;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class UserRestController {
    IUserService userService;

    @PostMapping
    public ResponseData<?> register(@RequestBody RegisterReqDTO registerReqDTO) {
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//        try {
//            registerReqDTO.setDob(String.valueOf(formatter.parse(registerReqDTO.getDob())));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        return ResponseData.builder().data(userService.register(registerReqDTO))
                .code(ErrorCode.CREATED.getCode())
                .message(ErrorCode.CREATED.getMessage())
                .status(ErrorCode.CREATED.getHttpStatus().name())
                .build();
    }
    @GetMapping("/{id}")
    public ResponseData<?> getUserById(@PathVariable Long id) {
        return ResponseData.builder()
                .status(ErrorCode.GET_SUCCESSFUL.getHttpStatus().name())
                .code(ErrorCode.GET_SUCCESSFUL.getCode())
                .message(ErrorCode.GET_SUCCESSFUL.getMessage())
                .data(userService.getUserById(id))
                .build();
    }
    @DeleteMapping("/{id}")
    public ResponseData<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseData.builder()
                .code(ErrorCode.DELETE_SUCCESSFUL.getCode())
                .message(ErrorCode.DELETE_SUCCESSFUL.getMessage())
                .message("Delete user success")
                .build();
    }
    @PatchMapping("/{id}")
    public ResponseData<?> updateUser(@PathVariable Long id, @RequestBody RegisterReqDTO request) {
        return ResponseData.builder()
                .code(ErrorCode.UPDATE_SUCCESSFUL.getCode())
                .message(ErrorCode.UPDATE_SUCCESSFUL.getMessage())
                .data(userService.updateUser(id, request))
                .build();
    }
}
