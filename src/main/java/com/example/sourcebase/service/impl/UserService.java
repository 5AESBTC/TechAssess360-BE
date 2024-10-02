package com.example.sourcebase.service.impl;

import com.example.sourcebase.domain.*;
import com.example.sourcebase.repository.*;
import com.example.sourcebase.domain.dto.reqdto.user.RegisterReqDTO;
import com.example.sourcebase.domain.dto.reqdto.user.UserLoginReqDTO;
import com.example.sourcebase.domain.dto.resdto.user.UserResDTO;
import com.example.sourcebase.mapper.UserMapper;
import com.example.sourcebase.service.IUserService;
import com.example.sourcebase.util.ErrorCode;

import com.example.sourcebase.util.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import com.example.sourcebase.exception.AppException;

import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements IUserService {
    Log log = new Log();
    IUserRepository userRepository;
    IRoleRepository roleRepository;
    IUserRoleRepository userRoleRepository;
    UserMapper userMapper = UserMapper.INSTANCE;
    private final IRankRepository rankRepository;
    private final PositionRepository positionRepository;

    @Override
    public UserResDTO register(RegisterReqDTO registerReqDTO) {
        if (userRepository.existsUserByEmailIgnoreCaseOrUsernameIgnoreCaseOrPhoneNumber(
                registerReqDTO.getEmail(),
                registerReqDTO.getUsername(),
                registerReqDTO.getPhoneNumber())) {
            log.LogError(ErrorCode.USERNAME_EXISTS);
            throw new IllegalArgumentException("User with given email, username, or phone number already exists.");
        }
        User userNew = userMapper.toUser(registerReqDTO);
        User createdUser = userRepository.save(userNew);
        saveUserRole(userNew, roleRepository.findById(2L).orElseThrow(() -> new NoSuchElementException("Role not found")));
        saveRank(userNew, registerReqDTO.getPosition(), registerReqDTO.getLevel());
        UserResDTO resultUserResDTO = userMapper.toUserResDTO(createdUser);
        log.LogInfo(SuccessCode.CREATED);

        return resultUserResDTO;
    }

    private void saveRank(User user, String positionInput, String level) {

            Position position = positionRepository.findByName(positionInput);
        if (position == null) {
            throw new IllegalArgumentException("Position not found");
        }

        Rank rank = rankRepository.findByPositionIdAndLevel(position.getId(), level);
        // Thiết lập rank cho user và lưu user
        user.setRank(rank);
        userRepository.save(user);
    }

    @Override
    public String login(UserLoginReqDTO userLogin) {
        return null;
    }

    @Override
    public UserResDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResDTO(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
            user.setDeleted(true);
            userRepository.save(user);
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    @Override
    public UserResDTO updateUser(Long id, RegisterReqDTO request) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        User userToUpdate = userMapper.toUser(request);
        userToUpdate.setId(id);
//        userToUpdate.setCreatedBy(existingUser.getCreatedBy());
        User updatedUser = userRepository.save(userToUpdate);
        return userMapper.toUserResDTO(updatedUser);
    }
    @Transactional
    public void saveUserRole(User user, Role role) {
        UserRole userRole = new UserRole(user, role);
        userRoleRepository.save(userRole);
    }
}
