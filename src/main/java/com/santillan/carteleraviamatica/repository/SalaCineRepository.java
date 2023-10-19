package com.santillan.carteleraviamatica.repository;

import com.santillan.carteleraviamatica.model.entitie.SalaCine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaCineRepository extends JpaRepository<SalaCine, Integer> {
}
