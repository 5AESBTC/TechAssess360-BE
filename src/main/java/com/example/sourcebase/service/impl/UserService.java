package com.example.sourcebase.service.impl;

import com.example.sourcebase.domain.*;
import com.example.sourcebase.domain.dto.resdto.user.UserDetailResDTO;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.sourcebase.exception.AppException;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements IUserService, UserDetailsService {
    Log log = new Log();
    IUserRepository userRepository;
    IRoleRepository roleRepository;
    IUserRoleRepository userRoleRepository;
    JwtTokenProvider jwtTokenProvider;
    UserMapper userMapper = UserMapper.INSTANCE;
    IRankRepository rankRepository;
    IPositionRepository positionRepository;
    PasswordEncoder passwordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        var userRoles = user.getUserRoles();
        var customUserDetails = new CustomUserDetails(user, userRoles);
        return customUserDetails;
    }

    @Override
    public UserResDTO register(RegisterReqDTO registerReqDTO) {
        if (userRepository.existsUserByEmailIgnoreCaseOrUsernameIgnoreCaseOrPhoneNumber(
                registerReqDTO.getEmail(),
                registerReqDTO.getUsername(),
                registerReqDTO.getPhoneNumber())) {
            log.LogError(ErrorCode.USERNAME_EXISTS);
        }
        registerReqDTO.setPassword(passwordEncoder.encode(registerReqDTO.getPassword()));
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
        UserDetails userDetails = loadUserByUsername(userLogin.username());

        if (!passwordEncoder.matches(userLogin.password(), userDetails.getPassword())) {
            throw new IllegalStateException("Wrong password or username");
        }
        UserDetailResDTO userDetailResDto = getUserDetailBy(userLogin.username());

        return jwtTokenProvider.generateToken(userDetailResDto.getId(),
                userDetailResDto.getName(),
                userDetailResDto.getEmail(),
                userDetailResDto.getPhoneNumber(),
                userDetailResDto.getUsername(),
                userDetailResDto.getRank(),
                userDetailResDto.getUserRoles());
    }

    @Override
    public UserDetailResDTO getUserDetailBy(String username) {
        return userMapper.toUserDetailResDTO(userRepository.findUserByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    @Override
    public List<UserResDTO> getAllUser(){
        List<User> userResDTOs = userRepository.findAll();
        return userResDTOs.stream().map(userMapper::toUserResDTO).collect(Collectors.toList());
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

    @Override
    public List<UserResDTO> getAllUserHadSameProject(Long userId) {
        List<User> userList = userRepository.getAllUserHadSameProject(userId);

        return userList.stream().map(userMapper::toUserResDTO).collect(Collectors.toList());
    }
}
