package com.santillan.carteleraviamatica.service;

import com.santillan.carteleraviamatica.repository.SalaCineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaCineService {
    @Autowired
    SalaCineRepository salaCineRepository;
    //ELIMINACION LOGICA
    public void remove(Integer idSala){
        salaCineRepository.deleteById(idSala);
    }

}
