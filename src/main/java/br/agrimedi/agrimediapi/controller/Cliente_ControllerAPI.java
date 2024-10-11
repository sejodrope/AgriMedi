package br.agrimedi.agrimediapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.agrimedi.agrimediapi.entity.Cliente;
import br.agrimedi.agrimediapi.service.Cliente_Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/v1/clientes")
public class Cliente_ControllerAPI {

    @Autowired
    public Cliente_Service service;
    
    @GetMapping
    public ResponseEntity<List<Cliente>> getClientes(){
        var listaClientes = service.getAll();
        
        return new ResponseEntity<List<Cliente>>(listaClientes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cliente> postCliente(@RequestBody Cliente cliente) {
        if (cliente.getId() == 0) {
            service.save(cliente);
            return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
        }
        return new ResponseEntity<Cliente>(cliente, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/(id)")
    public ResponseEntity<Cliente> putCliente(@PathVariable long id, @RequestBody Cliente cliente) {
        var clienteAntigo = service.getById(id);
        if (clienteAntigo == null){
            return ResponseEntity.notFound().build();
        }

        clienteAntigo.setNome(cliente.getNome());
        clienteAntigo.setEndereco(cliente.getEndereco());
        clienteAntigo.setDataNascimento(cliente.getDataNascimento());

        service.save(clienteAntigo);

        return new ResponseEntity<>(clienteAntigo, HttpStatus.OK);


    }


}
