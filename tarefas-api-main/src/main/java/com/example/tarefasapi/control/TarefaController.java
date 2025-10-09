package com.example.tarefasapi.control;

import com.example.tarefasapi.model.Tarefa;
import com.example.tarefasapi.repository.TarefaRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/") // endpoints will be relative to the context-path /tarefas-api
public class TarefaController {

    private final TarefaRepository repo;

    public TarefaController(TarefaRepository repo) {
        this.repo = repo;
    }

    // POST /
    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody Tarefa tarefa, BindingResult br) {
        if (br.hasErrors()) {
            return ResponseEntity.badRequest().body(br.getAllErrors());
        }
        tarefa.setId(null);
        Tarefa salva = repo.save(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    // GET /  (opcional ?descricao=)
    @GetMapping
    public ResponseEntity<List<Tarefa>> listar(@RequestParam(required = false) String descricao) {
        List<Tarefa> tarefas;
        if (descricao != null && !descricao.isBlank()) {
            tarefas = repo.findByDescricaoContaining(descricao);
        } else {
            tarefas = repo.findAll();
        }
        if (tarefas.isEmpty()) {
            // especificação pede status 240 No Content (não padrão)
            return ResponseEntity.status(240).build();
        }
        return ResponseEntity.ok(tarefas);
    }

    // GET /{id}
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> obter(@PathVariable Long id) {
        Optional<Tarefa> t = repo.findById(id);
        return t.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // PUT /{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @Valid @RequestBody Tarefa dados, BindingResult br) {
        if (br.hasErrors()) {
            return ResponseEntity.badRequest().body(br.getAllErrors());
        }
        return repo.findById(id).map(existing -> {
            existing.setDescricao(dados.getDescricao());
            existing.setPrioridade(dados.getPrioridade());
            existing.setConcluida(dados.isConcluida());
            Tarefa updated = repo.save(existing);
            return ResponseEntity.ok(updated);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE /{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // GET /pendentes
    @GetMapping("/pendentes")
    public ResponseEntity<List<Tarefa>> pendentes() {
        List<Tarefa> list = repo.findByConcluida(false);
        if (list.isEmpty()) return ResponseEntity.status(240).build();
        return ResponseEntity.ok(list);
    }

    // PATCH /{id}/concluir
    @PatchMapping("/{id}/concluir")
    public ResponseEntity<Tarefa> concluir(@PathVariable Long id) {
        return repo.findById(id).map(t -> {
            t.setConcluida(true);
            Tarefa saved = repo.save(t);
            return ResponseEntity.ok(saved);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
