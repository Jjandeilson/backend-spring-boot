package br.com.web.controller;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.web.model.Loja;
import br.com.web.repository.LojaRepository;

@RestController
@RequestMapping("/lojas")
public class LojaController {

	@Autowired
	private LojaRepository lojaRepository;
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_SALVAR_LOJAS')")
	public ResponseEntity<Loja> salvar(@Valid @RequestBody Loja loja, HttpServletResponse response){
		Loja lojaSalvar = lojaRepository.save(loja);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(lojaSalvar.getIdLoja()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.ok(lojaSalvar);
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LOJAS')")
	public Optional<?> buscar(@PathVariable(value="id") Long id){
		return lojaRepository.findById(id);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ALTERAR_LOJAS')")
	public ResponseEntity<Loja> alterar(@Valid @PathVariable(value="id") Long id, @RequestBody Loja loja){
		Loja lojaAlterar = lojaRepository.getOne(id);
		
		BeanUtils.copyProperties(loja, lojaAlterar, "idLoja");
		lojaRepository.save(lojaAlterar);
		return ResponseEntity.ok(lojaAlterar);
	}
}
