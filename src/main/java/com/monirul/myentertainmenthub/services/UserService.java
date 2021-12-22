package com.monirul.myentertainmenthub.services;

import com.monirul.myentertainmenthub.controllers.dto.UserRegistrationDto;
import com.monirul.myentertainmenthub.models.User;

public interface UserService {
    User save(UserRegistrationDto registrationDto);

}
