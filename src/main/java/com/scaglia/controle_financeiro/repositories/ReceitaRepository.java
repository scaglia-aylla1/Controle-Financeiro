package com.scaglia.controle_financeiro.repositories;

import com.scaglia.controle_financeiro.models.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    List<Receita> findByDataBetween(Date inicio, Date fim); // Buscar receitas por período
}
