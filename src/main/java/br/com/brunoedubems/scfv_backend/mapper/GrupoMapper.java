package br.com.brunoedubems.scfv_backend.mapper;

import br.com.brunoedubems.scfv_backend.controller.request.GrupoRequest;
import br.com.brunoedubems.scfv_backend.controller.response.GrupoResponse;
import br.com.brunoedubems.scfv_backend.controller.response.UsuarioResponse;
import br.com.brunoedubems.scfv_backend.entity.Grupo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GrupoMapper {

    private final UsuarioMapper usuarioMapper;

    public GrupoResponse toGrupoResponse(Grupo grupo) {

        List<UsuarioResponse> usuarios = Optional.ofNullable(grupo.getUsuarios())
                .stream()
                .flatMap(List::stream)
                .map(UsuarioMapper::toUsuarioResponse)
                .toList();
        return new GrupoResponse(
                grupo.getId(),
                grupo.getNome(),
                grupo.getTecnico(),
                grupo.getFaixaEtaria(),
                usuarios
        );
    }

    public Grupo toEntity(GrupoRequest grupoRequest) {
        Grupo grupo = new Grupo();
        grupo.setNome(grupoRequest.nome());
        grupo.setTecnico(grupoRequest.tecnico());
        grupo.setFaixaEtaria(grupoRequest.faixaEtaria());
        return grupo;
    }

}
