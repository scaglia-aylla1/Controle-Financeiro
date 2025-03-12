package com.scaglia.model;

import java.time.LocalDate;

public class Receita extends Transacao{

    public Receita(int id, String descricao, double valor, LocalDate data, Categoria categoria) {
        super(id, descricao, valor, data, categoria);
    }
}
