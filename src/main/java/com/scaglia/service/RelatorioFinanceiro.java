package com.scaglia.service;

import com.scaglia.model.Categoria;
import com.scaglia.model.Transacao;

import java.util.List;

public class RelatorioFinanceiro {

    public double calcularSaldoMensal(List<Transacao> transacoes) {
        double saldo = 0.0;
        for (Transacao t : transacoes) {
            saldo += t.getValor(); // pode ser negativo para despesas
        }
        return saldo;
    }

    public double calcularGastoPorCategoria(List<Transacao> transacoes, Categoria categoria) {
        double gasto = 0.0;
        for (Transacao t : transacoes) {
            if (t.getCategoria().equals(categoria)) {
                gasto += t.getValor();
            }
        }
        return gasto;
    }
}
