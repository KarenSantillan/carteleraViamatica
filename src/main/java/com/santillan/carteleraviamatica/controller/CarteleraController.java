package com.santillan.carteleraviamatica.controller;

import com.santillan.carteleraviamatica.model.dto.DatePeliculaDTO;
import com.santillan.carteleraviamatica.model.entitie.Pelicula;
import com.santillan.carteleraviamatica.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@Controller
public class CarteleraController {
    @Autowired
    PeliculaService peliculaService;

    @GetMapping("/findMovie")
    public ResponseEntity<?> buscarPelicula(@RequestParam("q") String nombre, @RequestParam("s") Integer idSala){
        Pelicula pelicula = peliculaService.buscarPeliculaPorNombreYSala(nombre, idSala);
        if (pelicula != null){
            return ResponseEntity.ok(pelicula);
        }else{
            return ResponseEntity.badRequest().body("No se generaron resultados para su busqueda");
        }
    }

    @GetMapping("/movieByDate")
    public ResponseEntity<DatePeliculaDTO> peliculaPorFecha(@RequestParam("q")Date fechaPublicacion){
        //LISTA DE PELICULAS SEGUN FECHA
        List<Pelicula> peliculas = peliculaService.findByFechaPublicacion(fechaPublicacion);
        int cantPeliculasByDate = peliculas.size();
        //LISTA CON TODAS LAS PELICULAS
        List<Pelicula> allMovies = peliculaService.findAll();
        DatePeliculaDTO informacion = new DatePeliculaDTO(cantPeliculasByDate, allMovies);
        if (peliculas != null){
            return ResponseEntity.ok(informacion);
        }else{
            return  ResponseEntity.badRequest().body(informacion);
        }
    }

    @GetMapping("/estadoSala")
    public ResponseEntity<?> estadoSala(@RequestParam("q") String nombreSala){
        List<Pelicula> peliculasEstado = peliculaService.finByNombreSala(nombreSala);
        if (peliculasEstado.size() < 3){
            return ResponseEntity.ok("Sala casi vacía");
        } else if (peliculasEstado.size()>=3 && peliculasEstado.size()<=5) {
            return ResponseEntity.ok("Sala casi Llena");
        }
        else if (peliculasEstado.size() > 5){
            return ResponseEntity.ok("Sala Llena");
        }
        else {
            return ResponseEntity.badRequest().body("La sala ingresada es incorrecta");
        }
    }

}
