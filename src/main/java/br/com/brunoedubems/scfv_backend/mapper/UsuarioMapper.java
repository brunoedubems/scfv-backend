package br.com.brunoedubems.scfv_backend.mapper;

import br.com.brunoedubems.scfv_backend.controller.request.UsuarioRequest;
import br.com.brunoedubems.scfv_backend.controller.response.UsuarioResponse;
import br.com.brunoedubems.scfv_backend.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;

@Component
public class UsuarioMapper {

    private UsuarioMapper() {
    }

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
               usuario.getGrupo() != null ? usuario.getGrupo().getId() : null,
               usuario.isPrioritario(),
               usuario.getSituacoes()
        );
    }
}
