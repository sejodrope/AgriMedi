package br.agrimedi.agrimediweb.service;

import java.util.List;

import br.agrimedi.agrimediweb.entity.Produto;

public interface ProdutoService {
    List<Produto> getAll();
    Produto save(Produto produto);
    Produto delete(long id);
    Produto getById(long id);
}
