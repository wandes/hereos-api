package br.com.hereos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.hereos.model.Hero;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {

}
