package br.com.brunoedubems.scfv_backend.controller;

import br.com.brunoedubems.scfv_backend.controller.request.UsuarioRequest;
import br.com.brunoedubems.scfv_backend.controller.response.UsuarioResponse;
import br.com.brunoedubems.scfv_backend.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario" )
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    //private final GrupoService grupoService;


    @GetMapping()
    public ResponseEntity<List<UsuarioResponse>> buscarTodosOsUsuarios() {
        List<UsuarioResponse> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}" )
    public ResponseEntity<UsuarioResponse> buscarUsuarioPorId(@PathVariable Long id) {
        UsuarioResponse usuario = usuarioService.listarUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }


    @PostMapping()
    public ResponseEntity<UsuarioResponse> inserir(@RequestBody UsuarioRequest usuarioRequest) {
        UsuarioResponse usuarioSalvo = usuarioService.inserir(usuarioRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequest usuarioRequest) {

        UsuarioResponse response = usuarioService.atualizaUsuario(id, usuarioRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletaUsuario(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
