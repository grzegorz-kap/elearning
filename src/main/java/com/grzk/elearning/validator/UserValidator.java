package com.grzk.elearning.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.grzk.elearning.model.User;

@Component("userValidator")
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User)target;
		if(!user.getPassword().equals(user.getPasswordConfirm())){
			errors.rejectValue("password", "user.password.error.theSame");
			errors.rejectValue("passwordConfirm", "user.password.error.theSame");
		}
	}

}
