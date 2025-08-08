package br.com.brunoedubems.scfv_backend.repository;

import br.com.brunoedubems.scfv_backend.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.*;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

}
