package br.com.brunoedubems.scfv_backend.repository;

import br.com.brunoedubems.scfv_backend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
