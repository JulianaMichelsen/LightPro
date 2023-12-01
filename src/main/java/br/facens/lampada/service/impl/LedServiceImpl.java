package br.facens.lampada.service.impl;

import br.facens.lampada.domain.Led;
import br.facens.lampada.domain.dto.LedDTO;
import br.facens.lampada.repository.LedRepository;
import br.facens.lampada.service.LedService;
import br.facens.lampada.service.exceptions.DataIntegrityViolationException;
import br.facens.lampada.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LedServiceImpl implements LedService {

    @Autowired
    LedRepository ledRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public Led findById(Integer id){
        Optional<Led> obj = ledRepository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado"));
    }

    @Override
    public List<Led> findAll() {
        return ledRepository.findAll();
    }

    @Override
    public Led create(LedDTO obj) {
        return ledRepository.save(mapper.map(obj, Led.class));
    }

    @Override
    public Led update(LedDTO obj) {
        findByUpdate(obj);
        return ledRepository.save(mapper.map(obj, Led.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        ledRepository.deleteById(id);
    }


    private void findByUpdate(LedDTO obj){
        Optional<Led> led = ledRepository.findById(obj.getId());
        if (led.isPresent() && !led.get().getId().equals(obj.getId())){
            throw new DataIntegrityViolationException("O id já cadastrado no sistema.");
        }

    }

}
