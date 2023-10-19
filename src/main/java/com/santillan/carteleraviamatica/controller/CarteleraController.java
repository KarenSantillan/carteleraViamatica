package com.santillan.carteleraviamatica.controller;

import com.santillan.carteleraviamatica.model.dto.DatePeliculaDTO;
import com.santillan.carteleraviamatica.model.entitie.Pelicula;
import com.santillan.carteleraviamatica.service.PeliculaService;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.ValidationException; // Assuming you have the correct import for ValidationException

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;



@RestController
public class CarteleraController {
    @Autowired
    PeliculaService peliculaService;

    // Cargar el esquema JSON para /findMovie
    InputStream findMovieSchemaStream = getClass().getResourceAsStream("/estructura_json/findMovieRequestSchema.json");
    String findMovieSchemaJson = null;
    {
        try {
            findMovieSchemaJson = IOUtils.toString(findMovieSchemaStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace(); // Maneja la excepción
        }
    }

    private Schema findMovieRequestSchema = SchemaLoader.load(new JSONObject(findMovieSchemaJson));


    // Cargar el esquema JSON para /movieByDate
    InputStream movieByDateSchemaStream = getClass().getResourceAsStream("/estructura_json/movieByDateRequestSchema.json");
    String movieByDateSchemaJson = null;

    {
        try {
            movieByDateSchemaJson = IOUtils.toString(movieByDateSchemaStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace(); // Maneja la excepción
        }
    }

    private Schema movieByDateRequestSchema = SchemaLoader.load(new JSONObject(movieByDateSchemaJson));

    // Cargar el esquema JSON para /estadoSala
    InputStream estadoSalaSchemaStream = getClass().getResourceAsStream("/estructura_json/estadoSalaRequestSchema.json");
    String estadoSalaSchemaJson = null;

    {
        try {
            estadoSalaSchemaJson = IOUtils.toString(estadoSalaSchemaStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace(); // Maneja la excepción
        }
    }

    private Schema estadoSalaRequestSchema = SchemaLoader.load(new JSONObject(estadoSalaSchemaJson));


    @GetMapping("/findMovie")
    public ResponseEntity<?> buscarPelicula(@RequestParam("q") String nombre, @RequestParam("s") Integer idSala) {
        // Validar la solicitud para /findMovie
        try {
            //findMovieRequestSchema.validate(new JSONObject("{\"q\":\"" + nombre + "\",\"s\":" + idSala + "}"));
            findMovieRequestSchema.validate(new JSONObject().put("q", nombre).put("s", idSala));
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body("La solicitud para /findMovie no cumple con el esquema JSON: " + e.getMessage());
        }
        //
        Pelicula pelicula = peliculaService.buscarPeliculaPorNombreYSala(nombre, idSala);
            if (pelicula != null) {
                return ResponseEntity.ok(pelicula);
            } else {
                return ResponseEntity.badRequest().body("No se generaron resultados para su busqueda");
            }

    }

    @GetMapping("/movieByDate")
    public ResponseEntity<?> peliculaPorFecha(@RequestParam("q")Date fechaPublicacion){

        // Validar la solicitud para /movieByDate
        try {
            movieByDateRequestSchema.validate(new JSONObject("{\"q\":\"" + fechaPublicacion + "\"}"));
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body("La solicitud para /movieByDate no cumple con el esquema JSON: " + e.getMessage());
        }

        //LISTA DE PELICULAS SEGUN FECHA
        List<Pelicula> peliculas = peliculaService.findByFechaPublicacion(fechaPublicacion);
        int cantPeliculasByDate = peliculas.size();
        //LISTA CON TODAS LAS PELICULAS
        List<Pelicula> allMovies = peliculaService.findAll();
        DatePeliculaDTO informacion = new DatePeliculaDTO(cantPeliculasByDate, allMovies);
        if (peliculas != null){
            return ResponseEntity.ok(informacion);
        }else{
            return  ResponseEntity.badRequest().body("Por favor ingrese una fecha correcta");
        }
    }

    @GetMapping("/estadoSala")
    public ResponseEntity<?> estadoSala(@RequestParam("q") String nombreSala){

        // Validar la solicitud para /movieByDate
        try {
            estadoSalaRequestSchema.validate(new JSONObject("{\"q\":\"" + nombreSala + "\"}"));
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body("La solicitud para /estadoSala no cumple con el esquema JSON: " + e.getMessage());
        }

        //
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
