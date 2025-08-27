package br.com.brunoedubems.scfv_backend.mapper;

import br.com.brunoedubems.scfv_backend.controller.request.UsuarioRequest;
import br.com.brunoedubems.scfv_backend.controller.response.UsuarioResponse;
import br.com.brunoedubems.scfv_backend.entity.Grupo;
import br.com.brunoedubems.scfv_backend.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UsuarioMapper {

    private final GrupoMapper grupoMapper;

    public Usuario toUsuario(UsuarioRequest usuarioRequest) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequest.nome());
        usuario.setDataNascimento(usuarioRequest.dataNascimento());
        usuario.setCpf(usuarioRequest.cpf());
        usuario.setNis(usuarioRequest.nis());
        usuario.setRg(usuarioRequest.rg());
        usuario.setSexo(usuarioRequest.sexo());
        usuario.setTelefone(usuarioRequest.telefone());
        usuario.setNomeMae(usuarioRequest.nomeMae());
        usuario.setNomeResponsavel(usuarioRequest.nomeResponsavel());
        usuario.setPrioritario(usuarioRequest.prioritario());
        if (Objects.nonNull(usuarioRequest.situacoes())) {
            usuario.setSituacoes(new HashSet<>(usuarioRequest.situacoes()));
        }
        return usuario;
    }

    public UsuarioResponse toUsuarioResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getDataNascimento(),
                usuario.getCpf(),
                usuario.getNis(),
                usuario.getRg(),
                usuario.getSexo(),
                usuario.getTelefone(),
                usuario.getNomeMae(),
                usuario.getNomeResponsavel(),
                usuario.isPrioritario(),
                usuario.getSituacoes(),
                usuario.getGrupo() != null ? grupoMapper.toGrupoResumoResponse(usuario.getGrupo()) : null
        );
    }
    public void updateUsuario(Usuario usuarioExistente, UsuarioRequest usuarioRequest, Grupo grupo) {
        usuarioExistente.setNome(usuarioRequest.nome());
        usuarioExistente.setDataNascimento(usuarioRequest.dataNascimento());
        usuarioExistente.setCpf(usuarioRequest.cpf());
        usuarioExistente.setNis(usuarioRequest.nis());
        usuarioExistente.setRg(usuarioRequest.rg());
        usuarioExistente.setSexo(usuarioRequest.sexo());
        usuarioExistente.setTelefone(usuarioRequest.telefone());
        usuarioExistente.setNomeMae(usuarioRequest.nomeMae());
        usuarioExistente.setNomeResponsavel(usuarioRequest.nomeResponsavel());
        usuarioExistente.setPrioritario(usuarioRequest.prioritario());

        if (Objects.nonNull(usuarioRequest.situacoes())) {
            usuarioExistente.setSituacoes(new HashSet<>(usuarioRequest.situacoes()));
        } else {
            usuarioExistente.setSituacoes(new HashSet<>());
        }

        if (grupo != null) {
            usuarioExistente.setGrupo(grupo);
        }
    }
}
