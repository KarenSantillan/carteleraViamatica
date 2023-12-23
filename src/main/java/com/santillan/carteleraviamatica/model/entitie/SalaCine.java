package com.santillan.carteleraviamatica.model.entitie;

import java.util.Collection;
import java.util.Objects;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@Table(name = "sala_cine")
//@SQLDelete(sql = "UPDATE sala_cine SET deleted = true WHERE id_sala=?")
//@Where(clause = "deleted=false")
public class SalaCine {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_sala", nullable = false)
    private Integer idSala;
    @Basic
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    @Basic
    @Column(name = "estado", nullable = false)
    private Character estado;
//    @Basic
//    @Column(name = "deleted")
//    private boolean deleted = Boolean.FALSE;
    @OneToMany(mappedBy = "salaCineByIdSala")
    private Collection<PeliculaSalacine> peliculaSalacinesByIdSala;

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

}
