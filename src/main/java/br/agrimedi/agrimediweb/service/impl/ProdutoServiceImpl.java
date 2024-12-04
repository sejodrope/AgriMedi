package br.agrimedi.agrimediweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.agrimedi.agrimediweb.entity.Produto;
import br.agrimedi.agrimediweb.repository.ProdutoRepository;
import br.agrimedi.agrimediweb.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository repository;
    @Override
    public List<Produto> getAll() {
        return repository.findAll();
    }

    @Override
    public Produto save(Produto produto) {
        return repository.save(produto);
    }

    @Override
    public Produto delete(long id) {
        var prod = getById(id);
        repository.delete(prod);
        return prod;
    }

    @Override
    public Produto getById(long id) {
        return repository.getById(id);
    }
    
}
