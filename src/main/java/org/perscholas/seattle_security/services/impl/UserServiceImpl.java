package org.perscholas.seattle_security.services.impl;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.perscholas.seattle_security.models.User;
import org.perscholas.seattle_security.repositories.UserRepository;
import org.perscholas.seattle_security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private PasswordEncoder pswdEncoder;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder pswdEncoder) {
		this.userRepository = userRepository;
		this.pswdEncoder = pswdEncoder;
	}
	
	@Override
	public User save(User user) {
		user.setPassword(pswdEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User findById(Integer id) {
		Optional<User> optUser = userRepository.findById(id);
		if (optUser.isPresent()) {
			return optUser.get();
		}
		throw new EntityNotFoundException();
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
