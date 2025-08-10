package br.com.brunoedubems.scfv_backend.mapper;

import br.com.brunoedubems.scfv_backend.dto.UsuarioDTO;
import br.com.brunoedubems.scfv_backend.entity.Grupo;
import br.com.brunoedubems.scfv_backend.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setDataNascimento(usuario.getDataNascimento());
        dto.setCpf(usuario.getCpf());
        dto.setNis(usuario.getNis());
        dto.setRg(usuario.getRg());
        dto.setSexo(usuario.getSexo());
        dto.setTelefone(usuario.getTelefone());
        dto.setNomeMae(usuario.getNomeMae());
        dto.setNomeResponsavel(usuario.getNomeResponsavel());
        dto.setPrioritario(usuario.isPrioritario());

        if (usuario.getGrupo() != null) {
            dto.setId(usuario.getGrupo().getId());
        }

        return dto;
    }

    public Usuario toEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNome(dto.getNome());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setCpf(dto.getCpf());
        usuario.setNis(dto.getNis());
        usuario.setRg(dto.getRg());
        usuario.setSexo(dto.getSexo());
        usuario.setTelefone(dto.getTelefone());
        usuario.setNomeMae(dto.getNomeMae());
        usuario.setNomeResponsavel(dto.getNomeResponsavel());
        usuario.setPrioritario(dto.isPrioritario());

        if (usuario.getGrupo() != null) {
            dto.setId(usuario.getGrupo().getId());
        }

        return usuario;
    }

    public static Usuario toEntity(UsuarioDTO dto, Grupo grupo) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNome(dto.getNome());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setCpf(dto.getCpf());
        usuario.setNis(dto.getNis());
        usuario.setRg(dto.getRg());
        usuario.setSexo(dto.getSexo());
        usuario.setTelefone(dto.getTelefone());
        usuario.setNomeMae(dto.getNomeMae());
        usuario.setNomeResponsavel(dto.getNomeResponsavel());
        usuario.setPrioritario(dto.isPrioritario());
        usuario.setGrupo(grupo);
        return usuario;
    }


}
