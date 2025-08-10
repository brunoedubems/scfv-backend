package br.com.brunoedubems.scfv_backend.service;

import br.com.brunoedubems.scfv_backend.dto.GrupoDTO;
import br.com.brunoedubems.scfv_backend.entity.Grupo;
import br.com.brunoedubems.scfv_backend.mapper.GrupoMapper;
import br.com.brunoedubems.scfv_backend.repository.GrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GrupoService {
    private final GrupoRepository gruporepository;
    private final GrupoMapper grupoMapper;

    public GrupoDTO inserir(GrupoDTO grupoDTO) {
        Grupo grupo = grupoMapper.toEntity(grupoDTO);
        grupo = gruporepository.save(grupo);
        return grupoMapper.toDTO(grupo);

    }

    public List<GrupoDTO> listarGrupos() {
        List<Grupo> usuarios = gruporepository.findAll();
        return usuarios.stream().map(grupoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public GrupoDTO atualizaGrupo(Long id, GrupoDTO grupoDTO) {
        Optional<Grupo> grupoExistente = gruporepository.findById(id);
        if (grupoExistente.isPresent()) {
            Grupo grupoAtualizado = grupoMapper.toEntity(grupoDTO);
            grupoAtualizado.setId(id);
            Grupo grupoSalvo = gruporepository.save(grupoAtualizado);
            return grupoMapper.toDTO(grupoSalvo);
        }
        return null;
    }


    public void deletarGrupoPorId(Long id) {
        gruporepository.deleteById(id);
    }
}
