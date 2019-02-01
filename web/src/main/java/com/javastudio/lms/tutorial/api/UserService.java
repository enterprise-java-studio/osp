package com.javastudio.lms.tutorial.api;

import com.javastudio.lms.tutorial.dto.UserDTO;
import com.javastudio.lms.tutorial.model.to.User;

public interface UserService extends GeneralServiceApi<UserDTO> {

    User findByUsername(String username) ;

    User findByEmail(String email) ;
}
