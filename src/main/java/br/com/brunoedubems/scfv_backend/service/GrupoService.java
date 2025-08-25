package br.com.brunoedubems.scfv_backend.service;

import br.com.brunoedubems.scfv_backend.controller.request.GrupoRequest;
import br.com.brunoedubems.scfv_backend.controller.response.GrupoResponse;
import br.com.brunoedubems.scfv_backend.entity.Grupo;
import br.com.brunoedubems.scfv_backend.mapper.GrupoMapper;
import br.com.brunoedubems.scfv_backend.repository.GrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GrupoService {
    private final GrupoRepository gruporepository;
    private final GrupoMapper grupoMapper;

    public GrupoResponse inserir(GrupoRequest grupoRequest) {
        Grupo grupo = grupoMapper.toEntity(grupoRequest);
        grupo = gruporepository.save(grupo);
        return grupoMapper.toGrupoResponse(grupo);
    }

    public List<GrupoResponse> listarGrupos() {
        return gruporepository.findAll().stream().map(grupoMapper::toGrupoResponse).toList();
    }

//    public GrupoDTO atualizaGrupo(Long id, GrupoDTO grupoDTO) {
//        Optional<Grupo> grupoExistente = gruporepository.findById(id);
//        if (grupoExistente.isPresent()) {
//            Grupo grupoAtualizado = grupoMapper.toEntity(grupoDTO);
//            grupoAtualizado.setId(id);
//            Grupo grupoSalvo = gruporepository.save(grupoAtualizado);
//            return grupoMapper.toDTO(grupoSalvo);
//        }
//        return null;
//    }
//
//
//    public void deletarGrupoPorId(Long id) {
//        gruporepository.deleteById(id);
//    }
}
