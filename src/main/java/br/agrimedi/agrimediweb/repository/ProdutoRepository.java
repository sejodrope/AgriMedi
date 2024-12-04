package br.agrimedi.agrimediweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.agrimedi.agrimediweb.entity.Produto;

@Repository
public interface ProdutoRepository 
    extends JpaRepository<Produto,Long>{
    
}
