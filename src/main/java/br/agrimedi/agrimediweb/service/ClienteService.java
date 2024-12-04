package br.agrimedi.agrimediweb.service;

import java.util.List;

import br.agrimedi.agrimediweb.entity.Cliente;

public interface ClienteService {
    List<Cliente> getAll();
    Cliente save(Cliente cliente);
    Cliente delete(long id);
    Cliente getById(long id);
}
