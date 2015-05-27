package com.grzk.elearning.factory;

import com.grzk.elearning.dto.UserRegisterRequest;
import com.grzk.elearning.model.User;

public interface UserFactory {
	User createUserFromRegisterRequest(UserRegisterRequest registerRequest);
}
