package com.santillan.carteleraviamatica.service;

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

    //CREACION COMO PRUEBA
    public Pelicula createMovie(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    //ELIMINACION LOGICA
    public void remove(Integer idPelicula){
        peliculaRepository.deleteById(idPelicula);
    }

}
