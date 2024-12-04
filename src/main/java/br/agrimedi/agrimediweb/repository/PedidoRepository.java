package br.agrimedi.agrimediweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.agrimedi.agrimediweb.entity.Pedido;

@Repository
public interface PedidoRepository 
    extends JpaRepository<Pedido,Long>{
    
}
