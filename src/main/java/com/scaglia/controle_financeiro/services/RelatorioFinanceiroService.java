package com.scaglia.controle_financeiro.services;

import com.scaglia.controle_financeiro.models.Despesa;
import com.scaglia.controle_financeiro.models.Receita;
import com.scaglia.controle_financeiro.models.RelatorioFinanceiro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class RelatorioFinanceiroService {

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private DespesaService despesaService;

    public RelatorioFinanceiro gerarRelatorio(String emailUsuario, LocalDate periodoInicio, LocalDate periodoFim) {
        List<Receita> receitas = receitaService.buscarReceitasPorPeriodo(emailUsuario, periodoInicio, periodoFim);
        List<Despesa> despesas = despesaService.buscarDespesasPorPeriodo(emailUsuario, periodoInicio, periodoFim);

        BigDecimal totalReceitas = receitas.stream()
                .map(Receita::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalDespesas = despesas.stream()
                .map(Despesa::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new RelatorioFinanceiro(emailUsuario, periodoInicio, periodoFim, totalReceitas, totalDespesas);
    }
}
