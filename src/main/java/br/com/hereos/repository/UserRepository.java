package br.com.hereos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.common.base.Optional;

import br.com.hereos.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
	
}
