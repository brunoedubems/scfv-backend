package br.com.brunoedubems.scfv_backend.service;

import br.com.brunoedubems.scfv_backend.dto.GrupoDTO;
import br.com.brunoedubems.scfv_backend.entity.Grupo;
import br.com.brunoedubems.scfv_backend.exception.ResourceNotFoundException;
import br.com.brunoedubems.scfv_backend.mapper.GrupoMapper;
import br.com.brunoedubems.scfv_backend.repository.GrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GrupoService {
    private final GrupoRepository gruporepository;
    private final GrupoMapper grupoMapper;


    @Transactional(readOnly = true)
    public GrupoDTO findById(Long id) {
        Grupo grupo = gruporepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grupo não encontrado pelo id: " + id));
        return grupoMapper.toDTO(grupo);
    }

}
