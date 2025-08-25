package br.com.brunoedubems.scfv_backend.service;

import br.com.brunoedubems.scfv_backend.controller.request.UsuarioRequest;
import br.com.brunoedubems.scfv_backend.controller.response.UsuarioResponse;
import br.com.brunoedubems.scfv_backend.entity.Grupo;
import br.com.brunoedubems.scfv_backend.entity.Usuario;
import br.com.brunoedubems.scfv_backend.mapper.UsuarioMapper;
import br.com.brunoedubems.scfv_backend.repository.GrupoRepository;
import br.com.brunoedubems.scfv_backend.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final GrupoRepository grupoRepository;

//    @Transactional(readOnly = true)
//    public UsuarioDTO findById(Long id) {
//        Usuario usuario = usuarioRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado pelo id: " + id));
//        return usuarioMapper.toDTO(usuario);
//    }

    @Transactional
    public UsuarioResponse inserir(UsuarioRequest usuarioRequest) {
        Usuario usuarioNovo = usuarioMapper.toUsuario(usuarioRequest);

        if (usuarioRequest.grupoId() != null) {
            Grupo grupo = grupoRepository
                    .findById(usuarioRequest.grupoId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Grupo não encontrado: " + usuarioRequest.grupoId()
                    ));
            usuarioNovo.setGrupo(grupo);
        }
        Usuario usuarioSalvo = usuarioRepository.save(usuarioNovo);
        return usuarioMapper.toUsuarioResponse(usuarioSalvo);
    }


//
//@Transactional(readOnly = true)
//public List<UsuarioDTO> listarUsuarios() {
//    List<Usuario> usuarios = usuarioRepository.findAll();
//    return usuarios.stream().map(usuarioMapper::toDTO).toList();
//}
//
//
//@Transactional
//public UsuarioDTO atualizaUsuario(Long id, UsuarioDTO dto) {
//    Usuario usuarioExistente = usuarioRepository.findById(id)
//            .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado pelo ID: " + id));
//
//    Grupo grupo = grupoRepository.findById(dto.getGrupoId())
//            .orElseThrow(() -> new ResourceNotFoundException("Grupo não encontrado pelo ID: " + dto.getGrupoId()));
//
//    Usuario atualizado = UsuarioMapper.toEntity(dto, grupo);
//    atualizado.setId(usuarioExistente.getId());
//
//    return usuarioMapper.toDTO(usuarioRepository.save(atualizado));
//}
//
//
//@Transactional
//public void deletaUsuarioPorId(Long id) {
//    Usuario usuario = usuarioRepository.findById(id)
//            .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado pelo ID: " + id));
//    usuarioRepository.delete(usuario);
//}
}
