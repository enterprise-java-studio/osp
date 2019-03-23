package com.javastudio.tutorial.api;

import com.javastudio.tutorial.dto.UserDTO;

public interface UserService extends GeneralServiceApi<UserDTO> {

    UserDTO findByUsername(String username);

    UserDTO findByEmail(String email);
}
