package com.santillan.carteleraviamatica.model.entitie;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "sala_cine", schema = "cartelera_db", catalog = "")
public class SalaCine {
    private Integer idSala;
    private String nombre;
    private Integer estado;
    private Collection<PeliculaSalacine> peliculaSalacinesByIdSala;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_sala", nullable = false)
    public Integer getIdSala() {
        return idSala;
    }

    public void setIdSala(Integer idSala) {
        this.idSala = idSala;
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
    @Column(name = "estado", nullable = false)
    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaCine salaCine = (SalaCine) o;
        return Objects.equals(idSala, salaCine.idSala) && Objects.equals(nombre, salaCine.nombre) && Objects.equals(estado, salaCine.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSala, nombre, estado);
    }

    @OneToMany(mappedBy = "salaCineByIdSala")
    public Collection<PeliculaSalacine> getPeliculaSalacinesByIdSala() {
        return peliculaSalacinesByIdSala;
    }

    public void setPeliculaSalacinesByIdSala(Collection<PeliculaSalacine> peliculaSalacinesByIdSala) {
        this.peliculaSalacinesByIdSala = peliculaSalacinesByIdSala;
    }
}
