package br.agrimedi.agrimediapi.service;

import java.util.List;

import br.agrimedi.agrimediapi.entity.Cliente;


public interface Cliente_Service {
    List<Cliente> getAll();
    Cliente save(Cliente cliente);
    Cliente delete(long id);
    Cliente getById(long id);
}
