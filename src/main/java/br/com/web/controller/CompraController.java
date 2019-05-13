package br.com.web.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.web.model.Compra;
import br.com.web.repository.CompraRepository;

@RestController
@RequestMapping("/compras")
public class CompraController {
	
	@Autowired
	private CompraRepository compraRepository;

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_SALVAR_COMPRAS')")
	public ResponseEntity<Compra> salvar(@Valid @RequestBody Compra compra, HttpServletResponse response){
		Compra compraSalvar = compraRepository.save(compra);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(compraSalvar.getIdCompra()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.ok(compraSalvar);
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_LISTAR_COMPRAS')")
	public ResponseEntity<?> listar(){
		List<Compra> compras = compraRepository.findAll();
		
		return !compras.isEmpty() ?  ResponseEntity.ok(compras) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_COMPRAS')")
	public Optional<Compra> buscar(@PathVariable(value="id") Long id){
		return compraRepository.findById(id);
	}

}
