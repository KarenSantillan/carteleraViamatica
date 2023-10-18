package com.santillan.carteleraviamatica.model.entitie;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "pelicula_salacine", schema = "cartelera_db", catalog = "")
public class PeliculaSalacine {
    private Integer idPeliculaSala;
    private Date fechaPublicacion;
    private Date fechaFin;
   // private Integer idSala;
    //private Integer idPelicula;
    private SalaCine salaCineByIdSala;
    private Pelicula peliculaByIdPelicula;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_pelicula_sala", nullable = false)
    public Integer getIdPeliculaSala() {
        return idPeliculaSala;
    }

    public void setIdPeliculaSala(Integer idPeliculaSala) {
        this.idPeliculaSala = idPeliculaSala;
    }

    @Basic
    @Column(name = "fecha_publicacion", nullable = true)
    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    @Basic
    @Column(name = "fecha_fin", nullable = true)
    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
/*
    @Basic
    @Column(name = "id_sala", nullable = true)
    public Integer getIdSala() {
        return idSala;
    }

    public void setIdSala(Integer idSala) {
        this.idSala = idSala;
    }

    @Basic
    @Column(name = "id_pelicula", nullable = true)
    public Integer getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Integer idPelicula) {
        this.idPelicula = idPelicula;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeliculaSalacine that = (PeliculaSalacine) o;
        return Objects.equals(idPeliculaSala, that.idPeliculaSala) && Objects.equals(fechaPublicacion, that.fechaPublicacion) && Objects.equals(fechaFin, that.fechaFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPeliculaSala, fechaPublicacion, fechaFin);
    }

    @ManyToOne
    @JoinColumn(name = "id_sala", referencedColumnName = "id_sala")
    public SalaCine getSalaCineByIdSala() {
        return salaCineByIdSala;
    }

    public void setSalaCineByIdSala(SalaCine salaCineByIdSala) {
        this.salaCineByIdSala = salaCineByIdSala;
    }

    @ManyToOne
    @JoinColumn(name = "id_pelicula", referencedColumnName = "id_pelicula")
    public Pelicula getPeliculaByIdPelicula() {
        return peliculaByIdPelicula;
    }

    public void setPeliculaByIdPelicula(Pelicula peliculaByIdPelicula) {
        this.peliculaByIdPelicula = peliculaByIdPelicula;
    }
}
