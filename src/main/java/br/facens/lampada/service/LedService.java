package br.facens.lampada.service;

import br.facens.lampada.domain.Led;
import br.facens.lampada.domain.dto.LedDTO;

import java.util.List;

public interface LedService {

    Led findById(Integer id);

    List<Led> findAll();

    Led create(LedDTO obj);
    Led update(LedDTO obj);
    void delete(Integer id);
}


