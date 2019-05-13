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

import br.com.web.model.Produto;
import br.com.web.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_SALVAR_PRODUTOS')")
	public ResponseEntity<Produto> salvar(@Valid @RequestBody Produto produto, HttpServletResponse response){
		Produto produtoSalvar = produtoRepository.save(produto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(produtoSalvar.getIdProduto()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.ok(produtoSalvar);
	}
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_LISTAR_PRODUTOS')")
	public ResponseEntity<?> listar(){
		List<Produto> produtos = produtoRepository.findAll();
		
		return !produtos.isEmpty() ? ResponseEntity.ok(produtos) : ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PRODUTOS')")
	public Optional<Produto> buscar(@PathVariable(value="id") Long id){
		return produtoRepository.findById(id);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ALTERAR_PRODUTOS')")
	public ResponseEntity<Produto> alterar(@Valid @PathVariable(value="id") Long id, @RequestBody Produto produto){
		Produto produtoAlterar = produtoRepository.getOne(id);
		
		BeanUtils.copyProperties(produto, produtoAlterar, "idProduto");
		
		produtoRepository.save(produtoAlterar);
		
		return ResponseEntity.ok(produtoAlterar);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PRODUTOS')")
	public void deletar(@PathVariable(value="id") Long id) {
		Produto produtoDeletar = produtoRepository.getOne(id);
		
		produtoRepository.delete(produtoDeletar);
	}
}
