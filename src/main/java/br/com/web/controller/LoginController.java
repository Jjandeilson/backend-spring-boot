package br.com.web.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.web.model.Login;
import br.com.web.repository.LoginRepository;

@RestController
@RequestMapping("/logins")
public class LoginController {

	@Autowired
	private LoginRepository loginRepository;
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_SALVAR_LOGINS')")
	public ResponseEntity<Login> salvar(@Valid @RequestBody Login login, HttpServletResponse response){
		Login loginSalvar = new Login();
		loginSalvar.setSenha(new BCryptPasswordEncoder().encode(login.getSenha()));
		loginRepository.save(login);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(loginSalvar.getLogin()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.ok(loginSalvar);
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_LISTAR_LOGINS')")
	public ResponseEntity<?> listar(){
		List<Login> logins = loginRepository.findAll();
		
		return !logins.isEmpty() ? ResponseEntity.ok(logins) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LOGINS')")
	public Optional<Login> buscar(@PathVariable(value="id") String id){
		return loginRepository.findById(id);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ALTERAR_LOGINS')")
	public ResponseEntity<Login> alterar(@Valid @PathVariable(value="id") String id, @RequestBody Login login){
		Login loginAlterar = loginRepository.getOne(id);
		
		BeanUtils.copyProperties(login, loginAlterar, "login");
		
		loginRepository.save(loginAlterar);
		
		return ResponseEntity.ok(loginAlterar);
	}
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_LOGINS')")
	public void deletar(@PathVariable(value="id") String id) {
		Login loginDeletar = loginRepository.getOne(id);
		
		loginRepository.delete(loginDeletar);
	}
}
