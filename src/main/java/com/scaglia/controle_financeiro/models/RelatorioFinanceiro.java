package com.scaglia.controle_financeiro.models;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RelatorioFinanceiro {
    private LocalDate periodoInicio;    // Início do período do relatório
    private LocalDate periodoFim;       // Fim do período do relatório
    private String usuarioEmail;         // Email do usuário
    private BigDecimal totalReceitas;   // Soma total das receitas
    private BigDecimal totalDespesas;    // Soma total das despesas
    private BigDecimal saldo;            // Saldo final (receitas - despesas)

    public RelatorioFinanceiro(String usuarioEmail, LocalDate periodoInicio, LocalDate periodoFim, BigDecimal totalReceitas, BigDecimal totalDespesas) {
        this.usuarioEmail = usuarioEmail;
        this.periodoInicio = periodoInicio;
        this.periodoFim = periodoFim;
        this.totalReceitas = totalReceitas;
        this.totalDespesas = totalDespesas;
        this.saldo = totalReceitas.subtract(totalDespesas);
    }
}