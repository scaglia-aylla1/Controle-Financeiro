package com.scaglia.controle_financeiro.controllers;

import com.scaglia.controle_financeiro.models.RelatorioFinanceiro;
import com.scaglia.controle_financeiro.services.RelatorioFinanceiroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Tag(name = "Relatórios", description = "Endpoints para gerar relatórios")
@RestController
@RequestMapping("/api/relatorios")
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioFinanceiroService relatorioFinanceiroService;

    @GetMapping
    public ResponseEntity<RelatorioFinanceiro> gerarRelatorio(
            @RequestParam String emailUsuario,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate periodoInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate periodoFim) {

        RelatorioFinanceiro relatorio = relatorioFinanceiroService.gerarRelatorio(emailUsuario, periodoInicio, periodoFim);
        return ResponseEntity.ok(relatorio);
    }
}