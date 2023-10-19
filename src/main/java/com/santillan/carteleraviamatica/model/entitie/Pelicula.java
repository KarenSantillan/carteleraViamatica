package com.santillan.carteleraviamatica.model.entitie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "pelicula", schema = "cartelera_db", catalog = "")
@SQLDelete(sql = "UPDATE sala_cine SET deleted = true WHERE id_sala=?")
@Where(clause = "deleted=false")
public class Pelicula {
    private Integer idPelicula;
    private String nombre;
    private Integer duracion;
    private Collection<PeliculaSalacine> peliculaSalacinesByIdPelicula;
    private boolean deleted = Boolean.FALSE;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_pelicula", nullable = false)
    public Integer getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Integer idPelicula) {
        this.idPelicula = idPelicula;
    }

    @Basic
    @Column(name = "nombre", nullable = false, length = 50)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "duracion", nullable = false)
    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    @Basic
    @Column(name = "deleted")
    public Boolean getDeleted(){ return deleted;}
    public void setDeleted(Boolean deleted){this.deleted = deleted;}

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

    @OneToMany(mappedBy = "peliculaByIdPelicula")
    @JsonIgnore
    public Collection<PeliculaSalacine> getPeliculaSalacinesByIdPelicula() {
        return peliculaSalacinesByIdPelicula;
    }

    public void setPeliculaSalacinesByIdPelicula(Collection<PeliculaSalacine> peliculaSalacinesByIdPelicula) {
        this.peliculaSalacinesByIdPelicula = peliculaSalacinesByIdPelicula;
    }
}
