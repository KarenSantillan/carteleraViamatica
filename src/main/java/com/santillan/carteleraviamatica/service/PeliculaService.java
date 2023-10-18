package com.santillan.carteleraviamatica.service;

import com.santillan.carteleraviamatica.model.dto.PeliculaDTO;
import com.santillan.carteleraviamatica.model.entitie.Pelicula;
import com.santillan.carteleraviamatica.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.sql.Date;
import java.util.List;

@Service
public class PeliculaService {
    @Autowired
    PeliculaRepository peliculaRepository;
    @Autowired
    ModelMapper modelMapper;
/*
    public PeliculaDTO getPeliculaDTOById(Integer id){
        Pelicula pelicula = peliculaRepository.findById(id).orElse(null);
        if(pelicula != null){
            return modelMapper.map(pelicula, PeliculaDTO.class);
        }
        return null;
    }*/

    public Pelicula buscarPeliculaPorNombreYSala(String nombre, Integer idSala){
        return peliculaRepository.findByPeliculaNombreAndSalaCineIdSala(nombre, idSala);

    }
    public List<Pelicula> findAll(){
        return peliculaRepository.findAll();
    }

    public List<Pelicula> findByFechaPublicacion(Date fechaPublicacion){
        return peliculaRepository.findByFechaPublicacion(fechaPublicacion);

    }

    public List<Pelicula> finByNombreSala(String nombreSala){
        return peliculaRepository.finByNombreSala(nombreSala);
    }

}
