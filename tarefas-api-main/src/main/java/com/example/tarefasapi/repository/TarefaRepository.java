package com.example.tarefasapi.repository;

import com.example.tarefasapi.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByDescricaoContaining(String descricao);
    List<Tarefa> findByConcluida(boolean concluida);
}
