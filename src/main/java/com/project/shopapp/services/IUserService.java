package com.project.shopapp.services;

import com.project.shopapp.dtos.UserDTO;
import com.project.shopapp.models.User;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public interface IUserService {
    User createUser(UserDTO userDTO) throws Exception;

//    String login(String phoneNumber, String password);

    String login(
            String phoneNumber,
            String password
//            , Long roleId
    ) throws Exception;
    List<User> getAllUsers();
    User getUserById(Long id) throws Exception;

}
