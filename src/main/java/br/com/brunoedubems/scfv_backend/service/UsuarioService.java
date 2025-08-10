package br.com.brunoedubems.scfv_backend.service;

import br.com.brunoedubems.scfv_backend.dto.UsuarioDTO;
import br.com.brunoedubems.scfv_backend.entity.Grupo;
import br.com.brunoedubems.scfv_backend.entity.Usuario;
import br.com.brunoedubems.scfv_backend.exception.ResourceNotFoundException;
import br.com.brunoedubems.scfv_backend.mapper.UsuarioMapper;
import br.com.brunoedubems.scfv_backend.repository.GrupoRepository;
import br.com.brunoedubems.scfv_backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final GrupoRepository grupoRepository;

    @Transactional(readOnly = true)
    public UsuarioDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado pelo id: " + id));
        return usuarioMapper.toDTO(usuario);
    }

    @Transactional(readOnly = true)
    public UsuarioDTO inserir(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario = usuarioRepository.save(usuario);

        return usuarioMapper.toDTO(usuario);

    }

    @Transactional(readOnly = true)
    public List<UsuarioDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(usuarioMapper::toDTO).toList();
    }


    @Transactional
    public UsuarioDTO atualizaUsuario(Long id, UsuarioDTO dto) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado pelo ID: " + id));

        Grupo grupo = grupoRepository.findById(dto.getGrupoId())
                .orElseThrow(() -> new ResourceNotFoundException("Grupo não encontrado pelo ID: " + dto.getGrupoId()));

        Usuario atualizado = UsuarioMapper.toEntity(dto, grupo);
        atualizado.setId(usuarioExistente.getId());

        return usuarioMapper.toDTO(usuarioRepository.save(atualizado));
    }


    @Transactional
    public void deletaUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado pelo ID: " + id));
        usuarioRepository.delete(usuario);
    }
}
