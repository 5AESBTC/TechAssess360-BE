package com.example.sourcebase.controller;

import com.example.sourcebase.domain.User;
import com.example.sourcebase.domain.dto.reqdto.user.RegisterReqDTO;
import com.example.sourcebase.domain.dto.resdto.user.UserResDTO;
import com.example.sourcebase.service.IUserService;
import com.example.sourcebase.util.ErrorCode;
import com.example.sourcebase.util.ResponseData;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import com.example.sourcebase.util.SuccessCode;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


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
                .code(SuccessCode.CREATED.getCode())
                .message(SuccessCode.CREATED.getMessage())
                .status(SuccessCode.CREATED.getHttpStatus().name())
                .code(SuccessCode.CREATED.getCode())
                .message(SuccessCode.CREATED.getMessage())
                .status(SuccessCode.CREATED.getHttpStatus().name())
                .build();
    }
    @GetMapping("/{id}")
    public ResponseData<?> getUserById(@PathVariable Long id) {
        return ResponseData.builder()
                .status(SuccessCode.GET_SUCCESSFUL.getHttpStatus().name())
                .code(SuccessCode.GET_SUCCESSFUL.getCode())
                .message(SuccessCode.GET_SUCCESSFUL.getMessage())
                .data(userService.getUserById(id))
                .build();
    }
    @DeleteMapping("/{id}")
    public ResponseData<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseData.builder()
                .code(SuccessCode.DELETE_SUCCESSFUL.getCode())
                .message(SuccessCode.DELETE_SUCCESSFUL.getMessage())
                .message("Delete user success")
                .build();
    }
    @PatchMapping("/{id}")
    public ResponseData<?> updateUser(@PathVariable Long id, @RequestBody RegisterReqDTO request) {
        return ResponseData.builder()
                .code(SuccessCode.UPDATE_SUCCESSFUL.getCode())
                .message(SuccessCode.UPDATE_SUCCESSFUL.getMessage())
                .data(userService.updateUser(id, request))
                .build();
    }
}
