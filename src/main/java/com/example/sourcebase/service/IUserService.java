package com.example.sourcebase.service;

import com.example.sourcebase.domain.User;
import com.example.sourcebase.domain.UserRole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IUserService {

//    void register(RegisterReqDto userNew, MultipartFile avatar) throws IOException;
//    void update(Long id, UserUpdateReqDto userEdit, MultipartFile avatar) throws IOException;
//    UserDetailResDto getUserDetailBy(Long id);
//    Page<ListCustomerResDto> getAllUserBy(Pageable pageable, String search, ETypeUser typeUser);
//    String login(UserLoginReqDto userLogin);
//    UserDetailResDto getUserDetailBy(String username);
//
//    //    Optional<UserRole> findByUser(User user);
//    List<UserRole> findByUser(User user);
//
//    UserDetailResDto getUserByUsername(String username);

    void delete(Long id);
}
