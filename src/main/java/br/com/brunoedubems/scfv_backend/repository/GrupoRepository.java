package br.com.brunoedubems.scfv_backend.repository;

import br.com.brunoedubems.scfv_backend.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {
    @Query("SELECT g FROM Grupo g LEFT JOIN FETCH g.usuarios")
    List<Grupo> findAllWithUsuarios();

//    @Query("SELECT g FROM Grupo g LEFT JOIN FETCH g.usuarios WHERE g.id = :id")
//    Optional<Grupo> findByIdWithUsuarios(@Param("id") Long id);
}
