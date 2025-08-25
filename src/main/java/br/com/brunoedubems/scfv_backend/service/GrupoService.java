package br.com.brunoedubems.scfv_backend.service;

import br.com.brunoedubems.scfv_backend.controller.request.GrupoRequest;
import br.com.brunoedubems.scfv_backend.controller.response.GrupoResponse;
import br.com.brunoedubems.scfv_backend.entity.Grupo;
import br.com.brunoedubems.scfv_backend.mapper.GrupoMapper;
import br.com.brunoedubems.scfv_backend.repository.GrupoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GrupoService {
    private final GrupoRepository grupoRepository;
    private final GrupoMapper grupoMapper;

    public GrupoResponse inserir(GrupoRequest grupoRequest) {
        Grupo novoGrupo  = grupoMapper.toEntity(grupoRequest);
        Grupo grupoSalvo = grupoRepository.save(novoGrupo);
        return grupoMapper.toGrupoResponse(grupoSalvo);
    }

    @Transactional(readOnly = true)
    public List<GrupoResponse> listarGrupos() {
        return grupoRepository.findAll().stream().map(grupoMapper::toGrupoResponse).toList();
    }

    public GrupoResponse atualizar(Long id, GrupoRequest grupoRequest) {
        Optional<Grupo> grupoExistente = grupoRepository.findById(id);

        if (grupoExistente.isPresent()) {
            Grupo grupoAtualizado = grupoMapper.toEntity(grupoRequest);
            grupoAtualizado.setId(id);

            Grupo grupoSalvo = grupoRepository.save(grupoAtualizado);
            return grupoMapper.toGrupoResponse(grupoSalvo);
        }

        return null; // (pode lançar exceção em vez de retornar null)
    }
    public void deletarGrupoPorId(Long id) {
        Grupo grupo = grupoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Grupo não encontrado com id: " + id));
        grupoRepository.deleteById(id);
    }
}
