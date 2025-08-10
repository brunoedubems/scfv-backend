package br.com.brunoedubems.scfv_backend.service;

import br.com.brunoedubems.scfv_backend.dto.UsuarioDTO;
import br.com.brunoedubems.scfv_backend.entity.Usuario;
import br.com.brunoedubems.scfv_backend.exception.ResourceNotFoundException;
import br.com.brunoedubems.scfv_backend.mapper.UsuarioMapper;
import br.com.brunoedubems.scfv_backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @Transactional(readOnly = true)
    public UsuarioDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado pelo id: " + id));
        return usuarioMapper.toDTO(usuario);
    }

    public UsuarioDTO inserir(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario = usuarioRepository.save(usuario);

        return usuarioMapper.toDTO(usuario);

    }

    public List<UsuarioDTO> listarUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(usuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO atualizaUsuario(Long id, UsuarioDTO usuarioDTO){
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if(usuarioExistente.isPresent()){
            Usuario usuarioAtualizado = usuarioMapper.toEntity(usuarioDTO);
            usuarioAtualizado.setId(id);
            Usuario usuarioSalvo = usuarioRepository.save(usuarioAtualizado);
            return usuarioMapper.toDTO(usuarioSalvo);
        }
        return null;
    }

    public void deletarUsuarioPorId(Long id){
        usuarioRepository.deleteById(id);
    }
}
