package br.agrimedi.agrimediapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.agrimedi.agrimediapi.entity.Ciclo_Crescimento;

@Repository
public interface Ciclo_Crescimento_Repository extends JpaRepository<Ciclo_Crescimento, Long> {
    
}
