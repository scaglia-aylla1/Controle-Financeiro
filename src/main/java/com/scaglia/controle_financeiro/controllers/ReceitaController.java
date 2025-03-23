package com.scaglia.controle_financeiro.controllers;


import com.scaglia.controle_financeiro.dto.ReceitaDTO;
import com.scaglia.controle_financeiro.models.Receita;
import com.scaglia.controle_financeiro.services.ReceitaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/receitas")
@RequiredArgsConstructor
public class ReceitaController {

    private final ReceitaService receitaService;

    @PostMapping
    public ResponseEntity<Receita> cadastrarReceita(@Valid @RequestBody ReceitaDTO receitaDTO) {
        Receita novaReceita = receitaService.cadastrarReceita(receitaDTO);
        return ResponseEntity.ok(novaReceita);
    }


    @GetMapping
    public ResponseEntity<List<Receita>> listarReceitas() {
        List<Receita> receitas = receitaService.listarReceitas("usuario_fixo@exemplo.com");
        return ResponseEntity.ok(receitas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receita> buscarReceitaPorId(@PathVariable Long id) {
        return receitaService.buscarReceitaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receita> atualizarReceita(
            @PathVariable Long id, @Valid @RequestBody Receita receita) {
        Receita receitaAtualizada = receitaService.atualizarReceita(id, receita);
        return ResponseEntity.ok(receitaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirReceita(@PathVariable Long id) {
        receitaService.excluirReceita(id);
        return ResponseEntity.noContent().build();
    }
}