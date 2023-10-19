package com.santillan.carteleraviamatica.service;

import com.santillan.carteleraviamatica.repository.PeliculaSalaCineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeliculaSalacineService {
    @Autowired
    PeliculaSalaCineRepository peliculaSalaCineRepository;
    //ELIMINACION LOGICA
    public void remove(Integer idPeliculaSala){
        peliculaSalaCineRepository.deleteById(idPeliculaSala);
    }
}
