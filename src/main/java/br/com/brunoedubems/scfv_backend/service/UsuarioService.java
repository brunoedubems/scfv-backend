package br.com.brunoedubems.scfv_backend.service;

import br.com.brunoedubems.scfv_backend.controller.request.UsuarioRequest;
import br.com.brunoedubems.scfv_backend.controller.response.UsuarioResponse;
import br.com.brunoedubems.scfv_backend.entity.Grupo;
import br.com.brunoedubems.scfv_backend.entity.Usuario;
import br.com.brunoedubems.scfv_backend.exception.ResourceNotFoundException;
import br.com.brunoedubems.scfv_backend.mapper.UsuarioMapper;
import br.com.brunoedubems.scfv_backend.repository.GrupoRepository;
import br.com.brunoedubems.scfv_backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final GrupoRepository grupoRepository;

    @Transactional(readOnly = true)
    public UsuarioResponse findById(Long id) {
        Usuario usuario = usuarioRepository.findByIdWithGrupo(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado pelo id: " + id));
        return usuarioMapper.toUsuarioResponse(usuario);
    }

    @Transactional
    public UsuarioResponse inserir(UsuarioRequest usuarioRequest) {
        Usuario usuarioNovo = usuarioMapper.toUsuario(usuarioRequest);

        if (usuarioRequest.grupoId() != null) {
            // getReferenceById retorna proxy sem ir ao DB, eficiente se só quer relacionar
            usuarioNovo.setGrupo(grupoRepository.getReferenceById(usuarioRequest.grupoId()));
        }
        Usuario usuarioSalvo = usuarioRepository.save(usuarioNovo);
        return usuarioMapper.toUsuarioResponse(usuarioSalvo);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponse> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toUsuarioResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public UsuarioResponse listarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario não encontrado pelo id " + id));
        return usuarioMapper.toUsuarioResponse(usuario);
    }

    @Transactional
    public UsuarioResponse atualizaUsuario(Long id, UsuarioRequest usuarioRequest) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado pelo ID: " + id));

        Grupo grupo = null;
        if (usuarioRequest.grupoId() != null) {
            grupo = grupoRepository.findById(usuarioRequest.grupoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Grupo não encontrado pelo ID: " + usuarioRequest.grupoId()));
        }

        // Atualiza o usuário existente (entidade gerenciada)
        usuarioMapper.updateUsuario(usuarioExistente, usuarioRequest, grupo);

        // Opcional: salvar explicitamente (não estritamente necessário dentro de @Transactional,
        // mas é comum para deixar claro a intenção)
        Usuario salvo = usuarioRepository.save(usuarioExistente);

        return usuarioMapper.toUsuarioResponse(salvo);
    }


    @Transactional
    public void deletaUsuario(Long id) {
        // verifica existência (retorna 404 se não existir)
        if (!usuarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não encontrado pelo ID: " + id);
        }

        try {
            // deleteById evita carregar toda a entidade e coleções associadas
            usuarioRepository.deleteById(id);
            // opcional: flush para garantir que a operação atinja o DB agora e possamos capturar erros
            usuarioRepository.flush();
        } catch (EmptyResultDataAccessException ex) {
            // pode ocorrer em condições de corrida (outro request deletou antes)
            throw new ResourceNotFoundException("Usuário não encontrado pelo ID: " + id);
        } catch (DataIntegrityViolationException ex) {
            // FK ou outras constraints impediram a exclusão
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Não foi possível deletar o usuário: existem referências que impedem a remoção." );
        }
    }
}
