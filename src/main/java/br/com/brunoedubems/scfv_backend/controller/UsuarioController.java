package br.com.brunoedubems.scfv_backend.controller;

import br.com.brunoedubems.scfv_backend.controller.request.UsuarioRequest;
import br.com.brunoedubems.scfv_backend.controller.response.UsuarioResponse;
import br.com.brunoedubems.scfv_backend.entity.Usuario;
import br.com.brunoedubems.scfv_backend.mapper.UsuarioMapper;
import br.com.brunoedubems.scfv_backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    //private final GrupoService grupoService;


//    @GetMapping()
//    public ResponseEntity<List<UsuarioDTO>> mostrarTodosOsUsuarios() {
//        List<UsuarioDTO> usuarios = usuarioService.listarUsuarios();
//        return ResponseEntity.ok(usuarios);
//    }
//

    @PostMapping()
    public ResponseEntity<UsuarioResponse> inserir( @RequestBody UsuarioRequest usuarioRequest) {
        Usuario usuarioSalvo = usuarioService.save(usuarioRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(UsuarioMapper.toUsuarioResponse(usuarioSalvo));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> alteraUsuarioPorId(
//            @PathVariable Long id,
//            @RequestBody UsuarioDTO usuarioDTO) {
//
//        UsuarioDTO usuario = usuarioService.atualizaUsuario(id, usuarioDTO);
//
//        if (usuario != null) {
//            return ResponseEntity.ok(usuario);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body("Usuario com id: " + id + " não existe");
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletaUsuarioPorId(@PathVariable Long id) {
//        usuarioService.deletaUsuarioPorId(id);
//        return ResponseEntity.noContent().build();
//    }

}
