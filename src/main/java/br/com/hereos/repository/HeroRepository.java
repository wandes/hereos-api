package br.com.hereos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hereos.model.Hero;

public interface HeroRepository extends JpaRepository<Hero, Long> {

}
