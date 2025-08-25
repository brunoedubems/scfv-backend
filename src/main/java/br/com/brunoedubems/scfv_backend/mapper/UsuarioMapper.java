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

    public static Usuario toUsuario(UsuarioRequest r) {
        Usuario u = new Usuario();
        u.setNome(r.nome());
        u.setDataNascimento(r.dataNascimento());
        u.setCpf(r.cpf());
        u.setNis(r.nis());
        u.setRg(r.rg());
        u.setSexo(r.sexo());
        u.setTelefone(r.telefone());
        u.setNomeMae(r.nomeMae());
        u.setNomeResponsavel(r.nomeResponsavel());
        u.setPrioritario(r.prioritario());

        if (Objects.nonNull(r.situacoes())) {
            u.setSituacoes(new HashSet<>(r.situacoes()));
        }

        // NÃO setar grupo aqui: responsabilidade da Service
        return u;
    }

    public static UsuarioResponse toUsuarioResponse(Usuario u) {
        return new UsuarioResponse(
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
                u.getGrupo() != null ? u.getGrupo().getId() : null,
                u.isPrioritario(),
                u.getSituacoes()
        );
    }
}
