package br.agrimedi.agrimediapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.agrimedi.agrimediapi.entity.Cliente;
import br.agrimedi.agrimediapi.repository.Cliente_Repository;
import br.agrimedi.agrimediapi.service.Cliente_Service;

@Service
public class Cliente_Service_Impl implements Cliente_Service{

    @Autowired
    private Cliente_Repository repository;

    @Override
    public List<Cliente> getAll() {
        return repository.findAll();
    }

    @Override
    public Cliente save(Cliente cliente) {
        return repository.save(cliente);
    }

    @Override
    public Cliente delete(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Cliente getById(long id) {
        var retorno = repository.findById(id);
        if(retorno.isPresent())
            return retorno.get();
        return null;
    }
    
}

