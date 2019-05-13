package br.com.web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.web.model.Login;

public interface LoginRepository extends JpaRepository<Login, String>{

	public Optional<Login> findByLogin(String login);
}
