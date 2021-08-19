package org.perscholas.seattle_security.services;

import org.perscholas.seattle_security.models.User;

public interface UserService {
	User save(User user);
	User findById(Integer id);
	User findByUsername(String username);
}
