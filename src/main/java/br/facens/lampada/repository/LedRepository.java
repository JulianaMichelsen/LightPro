package br.facens.lampada.repository;

import br.facens.lampada.domain.Led;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LedRepository extends JpaRepository<Led, Integer> {

    Optional<Led> findById(Integer id);
}
