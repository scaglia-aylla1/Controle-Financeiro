package com.scaglia.controle_financeiro.controllers;

import com.scaglia.controle_financeiro.dto.DespesaDTO;
import com.scaglia.controle_financeiro.models.Despesa;
import com.scaglia.controle_financeiro.services.DespesaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/despesas")
@RequiredArgsConstructor
public class DespesaController {

    private final DespesaService despesaService;

    @PostMapping
    public ResponseEntity<Despesa> cadastrarDespesa(@Valid @RequestBody DespesaDTO despesaDTO) {
        Despesa novaDespesa = despesaService.cadastrarDespesa(despesaDTO.toEntity(), despesaDTO.getEmailUsuario());
        return ResponseEntity.ok(novaDespesa);
    }
    @GetMapping
    public ResponseEntity<List<Despesa>> listarDespesas() {
        List<Despesa> despesas = despesaService.listarDespesas("usuario_fixo@exemplo.com"); // Aqui também é a mesma abordagem
        return ResponseEntity.ok(despesas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Despesa> buscarDespesaPorId(@PathVariable Long id) {
        return despesaService.buscarDespesaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Despesa> atualizarDespesa(
            @PathVariable Long id, @Valid @RequestBody Despesa despesa) {
        Despesa despesaAtualizada = despesaService.atualizarDespesa(id, despesa);
        return ResponseEntity.ok(despesaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDespesa(@PathVariable Long id) {
        despesaService.excluirDespesa(id);
        return ResponseEntity.noContent().build();
    }
}