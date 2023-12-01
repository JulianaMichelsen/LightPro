package br.facens.lampada.controller;

import br.facens.lampada.domain.Led;
import br.facens.lampada.domain.dto.LedDTO;
import br.facens.lampada.service.LedService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/led")
public class LedController {

    private static final String ID = "/{id}";
    @Autowired
    ModelMapper mapper;

    @Autowired
    LedService ledService;

    @GetMapping(value = ID)
    public ResponseEntity<Led> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(ledService.findById(id), Led.class));
    }

    @GetMapping
    public ResponseEntity<List<Led>> findAll(){
        return ResponseEntity.ok(ledService.findAll().stream().map( x -> mapper.map(x,Led.class)).toList());
    }

    @PostMapping
    public ResponseEntity<LedDTO> create(@RequestBody LedDTO ledDTO){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID)
                .buildAndExpand(ledService.create(ledDTO).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<LedDTO> update(@PathVariable Integer id, @RequestBody LedDTO obj){
        obj.setId(id);
        return ResponseEntity.ok().body(mapper.map(ledService.update(obj), LedDTO.class));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<LedDTO> delete(@PathVariable Integer id){
        ledService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
