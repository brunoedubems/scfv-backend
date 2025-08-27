package br.com.brunoedubems.scfv_backend.repository;

import br.com.brunoedubems.scfv_backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.grupo WHERE u.id = :id")
    Optional<Usuario> findByIdWithGrupo(@Param("id") Long id);
}
