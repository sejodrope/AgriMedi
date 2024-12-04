package br.agrimedi.agrimediweb.service;

import java.util.List;

import br.agrimedi.agrimediweb.entity.Cidade;

public interface CidadeService {
    Cidade save(Cidade cidade);
    Cidade getById(long id);
    List<Cidade> getAll();
}
