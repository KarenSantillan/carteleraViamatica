package com.santillan.carteleraviamatica.model.dto;

import com.santillan.carteleraviamatica.model.entitie.Pelicula;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class DatePeliculaDTO {
    int cantPeliculasByDate;
    List<Pelicula> allMovies;
}