package br.com.brunoedubems.scfv_backend.controller;

import br.com.brunoedubems.scfv_backend.dto.GrupoDTO;
import br.com.brunoedubems.scfv_backend.dto.UsuarioDTO;
import br.com.brunoedubems.scfv_backend.service.GrupoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos")
@RequiredArgsConstructor
public class GrupoController {

    private final GrupoService grupoService;

    @GetMapping()
    public ResponseEntity<List<GrupoDTO>> mostrarTodosOsGrupos() {
        List<GrupoDTO> grupos = grupoService.listarGrupos();
        return ResponseEntity.ok(grupos);
    }

    @PostMapping()
    public ResponseEntity<GrupoDTO> inserir(@RequestBody GrupoDTO grupoDTO) {
        GrupoDTO grupoNovo = grupoService.inserir(grupoDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(grupoNovo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alteraGrupoPorId(
            @PathVariable Long id,
            @RequestBody GrupoDTO grupoDTO) {

        GrupoDTO grupo = grupoService.atualizaGrupo(id, grupoDTO);

        if (grupo != null) {
            return ResponseEntity.ok(grupo);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Grupo com id: " + id + " não existe");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletaGrupoPorId(@PathVariable Long id) {
        if (grupoService.listarGrupos() != null) {
            grupoService.deletarGrupoPorId(id);
            return ResponseEntity.ok("Grupo com o id " + id + " deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O grupo com o id " + id + " não encontrado");
        }
    }
}
