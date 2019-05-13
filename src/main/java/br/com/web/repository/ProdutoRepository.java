package br.com.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.web.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
