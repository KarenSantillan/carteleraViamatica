package com.santillan.carteleraviamatica.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.santillan.carteleraviamatica.model.dto.DatePeliculaDTO;
import com.santillan.carteleraviamatica.model.entitie.Pelicula;
import com.santillan.carteleraviamatica.service.PeliculaSalacineService;
import com.santillan.carteleraviamatica.service.PeliculaService;
import com.santillan.carteleraviamatica.service.SalaCineService;

import jakarta.validation.ValidationException;



@RestController
@RequestMapping
public class CarteleraController {
    @Autowired
    PeliculaService peliculaService;
    @Autowired
    PeliculaSalacineService peliculaSalacineService;
    @Autowired
    SalaCineService salaCineService;


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


    //POST

    // Cargar el esquema JSON para /saveMovie
    InputStream saveMovieSchemaStream = getClass().getResourceAsStream("/estructura_json/pelicula-schema.json");
    String saveMovieSchemaJson = null;
    {
        try {
            saveMovieSchemaJson = IOUtils.toString(saveMovieSchemaStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace(); // Maneja la excepción
        }
    }

    private Schema saveMovieRequestSchema = SchemaLoader.load(new JSONObject(saveMovieSchemaJson));







    @GetMapping("/findMovie")
    public ResponseEntity<?> buscarPelicula(@RequestParam("q") String nombre, @RequestParam("s") Integer idSala) {
        // Validar la solicitud para /findMovie
    	String jsonReqString = "{\"q\":\"" + nombre + "\",\"s\":" + idSala + "}";
    	JSONObject jsonReq = new JSONObject(jsonReqString);
        try {
            findMovieRequestSchema.validate(jsonReq);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body("La soliscitud para /findMovie no cumple con el esquema JSON: " + e.getMessage());
        }

        Pelicula pelicula = peliculaService.buscarPeliculaPorNombreYSala(nombre, idSala);
            if (pelicula != null) {
                return ResponseEntity.ok(pelicula);
            } else {
                return ResponseEntity.badRequest().body("No se generaron resultados para su busqueda");
            }

    }

    @SuppressWarnings("unused")
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
    //POST
    @PostMapping (path="/saveMovie", consumes= MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createMovie(@RequestBody Pelicula movie) {
        // Validar la solicitud para /saveMovie
        try {
            JSONObject movieJson = new JSONObject();
            movieJson.put("nombre", movie.getNombre());
            movieJson.put("duracion", movie.getDuracion());

            saveMovieRequestSchema.validate(movieJson);
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body("La solicitud para /saveMovie no cumple con el esquema JSON: " + e.getMessage());
        }

        peliculaService.createMovie(movie);
        return ResponseEntity.ok("Pelicula creada con éxito");
    }


    //ELIMINACONES LOGICAS
    @DeleteMapping("/deletePelicula/{idPelicula}")
    public ResponseEntity<String> deletePelicula(@PathVariable Integer idPelicula) {
        peliculaService.remove(idPelicula);
        return ResponseEntity.ok("Pelicula eliminada con éxito");
    }
    @DeleteMapping("/deletePeliculaSala/{idPeliculaSala}")
    public ResponseEntity<String> deletePeliculaSalacine(@PathVariable Integer idPeliculaSala) {
        peliculaSalacineService.remove(idPeliculaSala);
        return ResponseEntity.ok("PeliculaSala eliminada con éxito");

    }
    @DeleteMapping("/deleteSala/{idSala}")
    public ResponseEntity<String> deleSalaCine(@PathVariable Integer idSala) {
        salaCineService.remove(idSala);
        return ResponseEntity.ok("Sala eliminada con éxito");

    }

}
