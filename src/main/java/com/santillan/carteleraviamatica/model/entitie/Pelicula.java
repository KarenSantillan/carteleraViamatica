package com.santillan.carteleraviamatica.model.entitie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Internal;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Collection;
import java.util.Objects;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pelicula", schema = "cartelera_db", catalog = "")
@SQLDelete(sql = "UPDATE pelicula SET deleted = true WHERE id_pelicula=?")
@Where(clause = "deleted=false")
public class Pelicula {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_pelicula", nullable = false)
    private Integer idPelicula;
    @Basic
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Basic
    @Column(name = "duracion", nullable = false)
    private Integer duracion;
    @OneToMany(mappedBy = "peliculaByIdPelicula")
    @JsonIgnore
    private Collection<PeliculaSalacine> peliculaSalacinesByIdPelicula;
    @Basic
    @Column(name = "deleted")
    private boolean deleted = Boolean.FALSE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pelicula pelicula = (Pelicula) o;
        return Objects.equals(idPelicula, pelicula.idPelicula) && Objects.equals(nombre, pelicula.nombre) && Objects.equals(duracion, pelicula.duracion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPelicula, nombre, duracion);
    }

}
