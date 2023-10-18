package com.santillan.carteleraviamatica.repository;

import com.santillan.carteleraviamatica.model.entitie.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {
    //Buscar la película de acuerdo con el nombre de la película e identificador de la sala
    @Query(value = "SELECT a.* FROM pelicula a JOIN pelicula_salacine psc ON psc.id_pelicula = a.id_pelicula JOIN sala_cine s ON psc.id_sala = s.id_sala WHERE a.nombre LIKE ?1 AND s.id_sala = ?2 ", nativeQuery = true)
    Pelicula findByPeliculaNombreAndSalaCineIdSala(String nombre, Integer idSala);

    //Presente las películas y devuelva la cantidad de las películas que se publican de acuerdo a una fecha(fecha_publicacion)
    @Query(value = "SELECT a.* FROM pelicula a JOIN pelicula_salacine psc ON psc.id_pelicula = a.id_pelicula JOIN sala_cine s ON psc.id_sala = s.id_sala WHERE psc.fecha_publicacion = ?1", nativeQuery = true)
    List<Pelicula> findByFechaPublicacion(Date fecha);

    //Se necesita buscar por el nombre de la sala de cine y presentar
    @Query(value = "SELECT p.* FROM pelicula p JOIN pelicula_salacine psc ON psc.id_pelicula = p.id_pelicula JOIN sala_cine s ON psc.id_sala = s.id_sala WHERE s.nombre = ?1 ", nativeQuery = true)
    List<Pelicula> finByNombreSala(String nombreSala);
}
