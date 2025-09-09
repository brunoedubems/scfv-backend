package br.com.brunoedubems.scfv_backend.mapper;

import br.com.brunoedubems.scfv_backend.controller.request.GrupoRequest;
import br.com.brunoedubems.scfv_backend.controller.response.GrupoResponse;
import br.com.brunoedubems.scfv_backend.controller.response.GrupoSimplesResponse;
import br.com.brunoedubems.scfv_backend.controller.response.UsuarioResponse;
import br.com.brunoedubems.scfv_backend.entity.Grupo;
import br.com.brunoedubems.scfv_backend.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GrupoMapper {

    private final UsuarioMapper usuarioMapper;
    public GrupoMapper(UsuarioMapper usuarioMapper) {
        this.usuarioMapper = usuarioMapper;
    }

    public GrupoResponse toGrupoResponse(Grupo grupo) {
        return new GrupoResponse(
                grupo.getId(),
                grupo.getNome(),
                grupo.getTecnico(),
                grupo.getFaixaEtaria(),
                mapUsuarios(grupo.getUsuarios())
        );
    }

    public Grupo toEntity(GrupoRequest grupoRequest) {
        Grupo grupo = new Grupo();
        grupo.setNome(grupoRequest.nome());
        grupo.setTecnico(grupoRequest.tecnico());
        grupo.setFaixaEtaria(grupoRequest.faixaEtaria());
        return grupo;
    }

    public GrupoSimplesResponse toGrupoResumoResponse(Grupo grupo) {
        return new GrupoSimplesResponse(
                grupo.getId(),
                grupo.getNome(),
                grupo.getTecnico(),
                grupo.getFaixaEtaria()
        );
    }

    private List<UsuarioResponse> mapUsuarios(List<Usuario> usuarios) {
        return Optional.ofNullable(usuarios)
                .stream()
                .flatMap(List::stream)
                .map(usuarioMapper::toUsuarioResponse)
                .toList();
    }




}
