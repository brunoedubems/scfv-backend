package br.com.brunoedubems.scfv_backend.mapper;

import br.com.brunoedubems.scfv_backend.controller.request.GrupoRequest;
import br.com.brunoedubems.scfv_backend.controller.response.GrupoResponse;
import br.com.brunoedubems.scfv_backend.controller.response.GrupoSimplesResponse;
import br.com.brunoedubems.scfv_backend.controller.response.UsuarioResponse;
import br.com.brunoedubems.scfv_backend.entity.Grupo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class GrupoMapper {

    public GrupoResponse toGrupoResponse(Grupo grupo) {
        List<UsuarioResponse> usuarios = Optional.ofNullable(grupo.getUsuarios())
                .stream()
                .flatMap(List::stream)
                .map(u -> new UsuarioResponse(
                        u.getId(),
                        u.getNome(),
                        u.getDataNascimento(),
                        u.getCpf(),
                        u.getNis(),
                        u.getRg(),
                        u.getSexo(),
                        u.getTelefone(),
                        u.getNomeMae(),
                        u.getNomeResponsavel(),
                        u.isPrioritario(),
                        u.getSituacoes() == null ? Set.of() : u.getSituacoes(),
                        null // evita recursão: não incluir GrupoResponse aqui
                ))
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

    public GrupoSimplesResponse toGrupoResumoResponse(Grupo grupo) {
        return new GrupoSimplesResponse(
                grupo.getId(),
                grupo.getNome(),
                grupo.getTecnico(),
                grupo.getFaixaEtaria()
        );
    }
}
