package br.agrimedi.agrimediweb.service;

import java.util.List;
import br.agrimedi.agrimediweb.entity.Pedido;

public interface PedidoService {
    List<Pedido> getAll();
    Pedido save(Pedido pedido);
    void delete(long id);
    Pedido getById(long id);
}
