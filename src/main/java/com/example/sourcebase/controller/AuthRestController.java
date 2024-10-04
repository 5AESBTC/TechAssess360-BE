package com.example.sourcebase.controller;

import com.example.sourcebase.domain.dto.reqdto.user.RegisterReqDTO;
import com.example.sourcebase.domain.dto.reqdto.user.UserLoginReqDTO;
import com.example.sourcebase.service.IUserService;
import com.example.sourcebase.util.ResponseData;
import com.example.sourcebase.util.SuccessCode;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auths")
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AuthRestController {
    IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<ResponseData<?>> login(@RequestBody UserLoginReqDTO userLogin) {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.LOGIN.getCode())
                        .message(SuccessCode.LOGIN.getMessage())
                        .data(userService.login(userLogin))
                        .build()
        );
    }
    @PostMapping("/register")
    public ResponseEntity<ResponseData<?>> register(@RequestBody RegisterReqDTO registerReqDTO) {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.CREATED.getCode())
                        .message(SuccessCode.CREATED.getMessage())
                        .data(userService.register(registerReqDTO))
                        .build()
        );
    }

}
