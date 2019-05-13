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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.web.model.Empregado;
import br.com.web.repository.EmpregadoRepository;

@RestController
@RequestMapping("/empregados")
public class EmpregadoController {

	@Autowired
	private EmpregadoRepository empregadoRepository;
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_SALVAR_EMPREGADOS')")
	public ResponseEntity<Empregado> salvar(@Valid @RequestBody Empregado empregado, HttpServletResponse response){
		Empregado empregadoSalvar = empregadoRepository.save(empregado);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(empregadoSalvar.getIdEmpregado()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.ok(empregadoSalvar);
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_LISTAR_EMPREGADOS')")
	public ResponseEntity<?> listar(){
		List<Empregado> empregados = empregadoRepository.findAll();
		
		return !empregados.isEmpty() ? ResponseEntity.ok(empregados) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_EMPREGADOS')")
	public Optional<Empregado> buscar(@PathVariable(value="id") Long id){
		return empregadoRepository.findById(id);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ALTERAR_EMPREGADOS')")
	public ResponseEntity<Empregado> alterar(@Valid @PathVariable(value="id") Long id, @RequestBody Empregado empregado){
		Empregado empregadoAlterar = empregadoRepository.getOne(id);
		
		BeanUtils.copyProperties(empregado, empregadoAlterar, "idEmpregado");
		
		empregadoRepository.save(empregadoAlterar);
		
		return ResponseEntity.ok(empregadoAlterar);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_EMPREGADOS')")
	public void deletar(@PathVariable(value="id") Long id) {
		Empregado empregadoDeletar = empregadoRepository.getOne(id);
		
		empregadoRepository.delete(empregadoDeletar);
	}
}
