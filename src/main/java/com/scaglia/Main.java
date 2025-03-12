package com.scaglia;

import com.scaglia.model.Categoria;
import com.scaglia.model.Despesa;
import com.scaglia.model.Receita;
import com.scaglia.model.Transacao;
import com.scaglia.service.RelatorioFinanceiro;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Categoria alimentacao = new Categoria(1, "Alimentação");
        Categoria transporte = new Categoria(2, "Transporte");

        Transacao receita1 = new Receita(1, "Salário", 3000.0, LocalDate.now(), alimentacao);
        Transacao despesa1 = new Despesa(2, "Compra Supermercado", 150.0, LocalDate.now(), alimentacao, "Cartão");
        Transacao despesa2 = new Despesa(3, "Gasolina", 100.0, LocalDate.now(), transporte, "Boleto");

        List<Transacao> transacoes = new ArrayList<>();
        transacoes.add(receita1);
        transacoes.add(despesa1);
        transacoes.add(despesa2);

        RelatorioFinanceiro relatorio = new RelatorioFinanceiro();
        System.out.println("Saldo Mensal: " + relatorio.calcularSaldoMensal(transacoes));
        System.out.println("Gasto com Alimentação: " + relatorio.calcularGastoPorCategoria(transacoes, alimentacao));

    }
}