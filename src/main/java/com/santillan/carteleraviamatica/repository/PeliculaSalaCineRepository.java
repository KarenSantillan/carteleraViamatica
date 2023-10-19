package com.santillan.carteleraviamatica.repository;

import com.santillan.carteleraviamatica.model.entitie.PeliculaSalacine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaSalaCineRepository extends JpaRepository<PeliculaSalacine, Integer> {
}
