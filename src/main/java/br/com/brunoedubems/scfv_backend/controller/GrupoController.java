package br.com.brunoedubems.scfv_backend.controller;

import br.com.brunoedubems.scfv_backend.controller.request.GrupoRequest;
import br.com.brunoedubems.scfv_backend.controller.response.GrupoResponse;
import br.com.brunoedubems.scfv_backend.mapper.GrupoMapper;
import br.com.brunoedubems.scfv_backend.service.GrupoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupo")
@RequiredArgsConstructor
public class GrupoController {

    private final GrupoService grupoService;
    private final GrupoMapper grupoMapper;

    @GetMapping()
    public ResponseEntity<List<GrupoResponse>> mostrarTodosOsGrupos() {
        List<GrupoResponse> grupos = grupoService.listarGrupos();
        return ResponseEntity.ok(grupos);
    }

    @PostMapping()
    public ResponseEntity<GrupoResponse> inserir(@RequestBody GrupoRequest grupoRequest) {
        GrupoResponse grupoNovo = grupoService.inserir(grupoRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(grupoNovo);
    };

    @PutMapping("/{id}")
    public ResponseEntity<GrupoResponse> atualizar(
            @PathVariable Long id,
            @RequestBody GrupoRequest grupoRequest) {

        GrupoResponse grupoResponse = grupoService.atualizar(id, grupoRequest);

        if (grupoResponse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(grupoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaUsuarioPorId(@PathVariable Long id) {
        grupoService.deletarGrupoPorId(id);
        return ResponseEntity.noContent().build();
    }
}
