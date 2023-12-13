package com.santillan.carteleraviamatica.model.entitie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.sql.Date;
import java.util.Objects;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pelicula_sala_cine")
//@SQLDelete(sql = "UPDATE pelicula_salacine SET deleted = true WHERE id_pelicula_sala=?")
//@Where(clause = "deleted=false")
public class PeliculaSalacine {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_pelicula_sala", nullable = false)
    private Integer idPeliculaSala;
    @Basic
    @Column(name = "fecha_publicacion", nullable = true)
    private Date fechaPublicacion;
    @Basic
    @Column(name = "fecha_fin", nullable = true)
    private Date fechaFin;
   @Basic
   @Column(name = "estado")
   private Character estado;
    @ManyToOne
    @JoinColumn(name = "id_sala_cine", referencedColumnName = "id_sala")
    private SalaCine salaCineByIdSala;
    @ManyToOne
    @JoinColumn(name = "id_pelicula", referencedColumnName = "id_pelicula")
    private Pelicula peliculaByIdPelicula;


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

}
