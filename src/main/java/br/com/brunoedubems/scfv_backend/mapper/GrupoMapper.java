package br.com.brunoedubems.scfv_backend.mapper;

import br.com.brunoedubems.scfv_backend.dto.GrupoDTO;
import br.com.brunoedubems.scfv_backend.entity.Grupo;
import br.com.brunoedubems.scfv_backend.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GrupoMapper {

    private final GrupoMapper grupoMapper;
    private final UsuarioMapper usuarioMapper;

    public GrupoDTO toDTO(Grupo grupo) {
        GrupoDTO dto = new GrupoDTO();
        dto.setId(grupo.getId());
        dto.setNome(grupo.getNome());
        dto.setTecnico(grupo.getTecnico());
        dto.setFaixaEtaria(grupo.getFaixaEtaria());

        if (grupo.getUsuarios() != null) {
            dto.setUsuarios(grupo.getUsuarios().stream()
                    .map(usuarioMapper::toDTO)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    public Grupo toEntity(GrupoDTO dto) {
        Grupo grupo = new Grupo();
        grupo.setId(dto.getId());
        grupo.setNome(dto.getNome());
        grupo.setTecnico(dto.getTecnico());
        grupo.setFaixaEtaria(dto.getFaixaEtaria());
        grupo.setUsuarios(null);
        return grupo;
    }

}
