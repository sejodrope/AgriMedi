package br.agrimedi.agrimediapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.agrimedi.agrimediapi.entity.Cliente;

@Repository
public interface Cliente_Repository extends JpaRepository<Cliente,Long>{
}

