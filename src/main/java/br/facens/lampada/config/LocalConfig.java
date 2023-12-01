package br.facens.lampada.config;

import br.facens.lampada.domain.Led;
import br.facens.lampada.repository.LedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    LedRepository ledRepository;

    @Bean
    public void start(){
        Led l1 = new Led(null, "fluorescente"); // amarela
        Led l2 = new Led(null, "incandescente"); // led
        Led l3 = new Led(null, "led");
        Led l4 = new Led(null, "incandescente");

        ledRepository.saveAll(List.of(l1,l2,l3,l4));
    }

}
