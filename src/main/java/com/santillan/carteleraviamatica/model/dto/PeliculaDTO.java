package com.santillan.carteleraviamatica.model.dto;

import com.santillan.carteleraviamatica.model.entitie.PeliculaSalacine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeliculaDTO {
    private Integer idPelicula;
    private String nombre;
    private Integer duracion;
    private Collection<PeliculaSalacine> peliculaSalacinesByIdPelicula;

}
