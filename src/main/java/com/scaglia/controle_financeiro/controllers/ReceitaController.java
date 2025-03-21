package com.scaglia.controle_financeiro.controllers;

import com.scaglia.controle_financeiro.models.Receita;
import com.scaglia.controle_financeiro.services.ReceitaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/receitas")
@RequiredArgsConstructor
public class ReceitaController {

    private final ReceitaService receitaService;

    @GetMapping
    public ResponseEntity<List<Receita>> listarTodas() {
        return ResponseEntity.ok(receitaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receita> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(receitaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Receita> salvar(@Valid @RequestBody Receita receita) {
        return ResponseEntity.ok(receitaService.salvar(receita));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receita> atualizar(@PathVariable Long id, @Valid @RequestBody Receita receitaAtualizada) {
        return ResponseEntity.ok(receitaService.atualizar(id, receitaAtualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        receitaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<Receita>> buscarPorPeriodo(
            @RequestParam Date inicio, @RequestParam Date fim) {
        return ResponseEntity.ok(receitaService.buscarPorPeriodo(inicio, fim));
    }
}
