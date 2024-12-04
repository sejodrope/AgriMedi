package br.agrimedi.agrimediweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.agrimedi.agrimediweb.entity.Cidade;

@Repository
public interface CidadeRepository 
    extends JpaRepository<Cidade,Long>{
    
}
