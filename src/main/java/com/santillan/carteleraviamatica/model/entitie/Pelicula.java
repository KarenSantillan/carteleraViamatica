package com.santillan.carteleraviamatica.model.entitie;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pelicula")
//@SQLDelete(sql = "UPDATE pelicula SET deleted = true WHERE id_pelicula=?")
//@Where(clause = "deleted=false")
public class Pelicula implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6501340117114301992L;
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
    @Column(name = "estado")
    private Character estado;

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
