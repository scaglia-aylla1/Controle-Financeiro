package com.scaglia.model;

import java.time.LocalDate;

public class Despesa extends Transacao{

  private String formaPagamento;

    public Despesa(int id, String descricao, double valor, LocalDate data, Categoria categoria, String formaPagamento) {
        super(id, descricao, valor, data, categoria);
        this.formaPagamento = formaPagamento;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}
