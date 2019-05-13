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

import br.com.web.model.Fornecedor;
import br.com.web.repository.FornecedorRepository;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {
	
	@Autowired
	private FornecedorRepository fornecedorRepository;

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_SALVAR_FORNECEDORES')")
	public ResponseEntity<Fornecedor> salvar(@Valid @RequestBody Fornecedor fornecedor, HttpServletResponse response){
		Fornecedor fornecedorSalvar = fornecedorRepository.save(fornecedor);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(fornecedorSalvar.getIdFornecedor()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.ok(fornecedorSalvar);
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_LISTAR_FORNECEDORES')")
	public ResponseEntity<?> listar(){
		List<Fornecedor> fornecedores = fornecedorRepository.findAll();
		
		return !fornecedores.isEmpty() ? ResponseEntity.ok(fornecedores) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_FORNECEDORES')")
	public Optional<Fornecedor> buscar(@PathVariable(value="id") Long id){
		return fornecedorRepository.findById(id);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ALTERAR_FORNECEDORES')")
	public ResponseEntity<Fornecedor> alterar(@Valid @PathVariable(value="id") Long id, @RequestBody Fornecedor fornecedor){
		Fornecedor fornecedorAlterar = fornecedorRepository.getOne(id);
		
		BeanUtils.copyProperties(fornecedor, fornecedorAlterar, "idFornecedor");
		
		fornecedorRepository.save(fornecedorAlterar);
		
		return ResponseEntity.ok(fornecedorAlterar);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_FORNECEDORES')")
	public void remover(@PathVariable(value="id") Long id) {
		Fornecedor fornecedorDeletar = fornecedorRepository.getOne(id);
		
		fornecedorRepository.delete(fornecedorDeletar);
	}
}
