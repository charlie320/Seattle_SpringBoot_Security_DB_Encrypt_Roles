package org.perscholas.seattle_security.repositories;

import org.perscholas.seattle_security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}