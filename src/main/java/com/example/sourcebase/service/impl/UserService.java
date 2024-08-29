package com.example.sourcebase.service.impl;

import com.example.sourcebase.domain.Role;
import com.example.sourcebase.domain.User;
import com.example.sourcebase.domain.UserRole;
import com.example.sourcebase.domain.dto.reqdto.user.RegisterReqDTO;
import com.example.sourcebase.domain.dto.reqdto.user.UserLoginReqDTO;
import com.example.sourcebase.domain.dto.resdto.user.UserResDTO;
import com.example.sourcebase.domain.enumeration.EGender;
import com.example.sourcebase.domain.enumeration.ETypeUser;
import com.example.sourcebase.mapper.UserMapper;
import com.example.sourcebase.repository.IRoleRepository;
import com.example.sourcebase.repository.IUserRepository;
import com.example.sourcebase.repository.IUserRoleRepository;
import com.example.sourcebase.service.IUserService;
import com.example.sourcebase.util.ErrorCode;
import com.example.sourcebase.util.FormatTimeAppUtil;
import com.example.sourcebase.util.ResponseData;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements IUserService {
    IUserRepository userRepository;
    IRoleRepository roleRepository;
    IUserRoleRepository userRoleRepository;
    UserMapper userMapper = UserMapper.INSTANCE;
    @Override
    public UserResDTO register(RegisterReqDTO registerReqDTO) {
        if (userRepository.existsUserByEmailIgnoreCaseOrUsernameIgnoreCaseOrPhoneNumber(
                registerReqDTO.getEmail(),
                registerReqDTO.getUsername(),
                registerReqDTO.getPhoneNumber())) {
            throw new IllegalArgumentException("User with given email, username, or phone number already exists.");
        }
        User userNew = userMapper.toUser(registerReqDTO);
//        saveUserRole(userNew, roleRepository.findById(1L).orElseThrow(() -> new NoSuchElementException("Role not found")));
        User createdUser = userRepository.save(userNew);
//        createdUser.setDob(createdUser.);
        UserResDTO resultUserResDTO= userMapper.toUserResDTO(createdUser);

        return resultUserResDTO;
    }
    @Override
    public String login(UserLoginReqDTO userLogin) {
        return null;
    }
    @Transactional
    public void saveUserRole(User user, Role role) {
        UserRole userRole = new UserRole(user, role);
        userRoleRepository.save(userRole);
    }
}
