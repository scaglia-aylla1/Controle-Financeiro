package com.scaglia.controle_financeiro.controllers;

import com.scaglia.controle_financeiro.dto.PagamentoDTO;
import com.scaglia.controle_financeiro.models.Despesa;
import com.scaglia.controle_financeiro.services.DespesaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/despesas")
public class DespesaController {

    @Autowired
    private DespesaService despesaService;

    @PostMapping
    public ResponseEntity<Despesa> criarDespesa(@Valid @RequestBody Despesa despesa) {
        return ResponseEntity.ok(despesaService.criarDespesa(despesa));
    }

    @GetMapping
    public ResponseEntity<List<Despesa>> listarDespesas() {
        return ResponseEntity.ok(despesaService.listarDespesas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Despesa> buscarDespesaPorId(@PathVariable Long id) {
        Optional<Despesa> despesa = despesaService.buscarDespesaPorId(id);
        return despesa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<Despesa>> buscarDespesasPorPeriodo(
            @RequestParam LocalDate inicio, @RequestParam LocalDate fim) {
        return ResponseEntity.ok(despesaService.buscarDespesasPorPeriodo(inicio, fim));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Despesa> editarDespesa(@PathVariable Long id, @RequestBody @Valid Despesa despesa) {
        Despesa despesaAtualizada = despesaService.editarDespesa(id, despesa);
        if (despesaAtualizada != null) {
            return ResponseEntity.ok(despesaAtualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/registrar-pagamento")
    public ResponseEntity<String> registrarPagamento(@RequestBody PagamentoDTO pagamentoDTO) {
        despesaService.registrarPagamento(pagamentoDTO.getIdDespesa());
        return ResponseEntity.ok("Pagamento registrado com sucesso");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDespesa(@PathVariable Long id) {
        despesaService.deletarDespesa(id);
        return ResponseEntity.noContent().build();
    }
}

