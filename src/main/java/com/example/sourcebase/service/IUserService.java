package com.example.sourcebase.service;

import com.example.sourcebase.domain.dto.reqdto.user.RegisterReqDTO;
import com.example.sourcebase.domain.dto.reqdto.user.UserLoginReqDTO;
import com.example.sourcebase.domain.dto.resdto.user.UserResDTO;
import com.example.sourcebase.util.ResponseData;

public interface IUserService {
    UserResDTO register(RegisterReqDTO userNew);
    String login(UserLoginReqDTO userLogin);

}
