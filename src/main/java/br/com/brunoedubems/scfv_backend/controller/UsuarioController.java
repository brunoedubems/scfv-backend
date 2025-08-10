package br.com.brunoedubems.scfv_backend.controller;

import br.com.brunoedubems.scfv_backend.dto.GrupoDTO;
import br.com.brunoedubems.scfv_backend.dto.UsuarioDTO;
import br.com.brunoedubems.scfv_backend.service.GrupoService;
import br.com.brunoedubems.scfv_backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final GrupoService grupoService;

    @GetMapping("/{id}")
    public ResponseEntity<GrupoDTO> findById(@PathVariable Long id) {
        GrupoDTO dto = grupoService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping()
    public ResponseEntity<List<UsuarioDTO>> mostrarTodosOsUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }


    @PostMapping()
    public ResponseEntity<UsuarioDTO> inserir(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO usuarioNovo = usuarioService.inserir(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioNovo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alteraUsuarioPorId(
            @PathVariable Long id,
            @RequestBody UsuarioDTO usuarioDTO) {

        UsuarioDTO usuario = usuarioService.atualizaUsuario(id, usuarioDTO);

        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com id: " + id + " não existe");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaUsuarioPorId(@PathVariable Long id){
        if(usuarioService.listarUsuarios() != null){
            usuarioService.deletarUsuarioPorId(id);
            return  ResponseEntity.ok("Usuario com o id " + id + " deletado com sucesso");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O usuario com o id " + id + " não encontrado");
        }
    }


}
