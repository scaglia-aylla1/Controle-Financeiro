package com.scaglia.controle_financeiro.services;

import com.scaglia.controle_financeiro.models.Receita;
import com.scaglia.controle_financeiro.repositories.ReceitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceitaService {

    private final ReceitaRepository receitaRepository;

    public List<Receita> listarTodas() {
        return receitaRepository.findAll();
    }

    public Receita buscarPorId(Long id) {
        return receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada"));
    }

    @Transactional
    public Receita salvarReceita(Receita receita) {
        return receitaRepository.save(receita);
    }

    @Transactional
    public Receita atualizar(Long id, Receita receitaAtualizada) {
        Receita receita = buscarPorId(id);
        receita.setDescricao(receitaAtualizada.getDescricao());
        receita.setValor(receitaAtualizada.getValor());
        receita.setData(receitaAtualizada.getData());
        receita.setCategoria(receitaAtualizada.getCategoria());
        return receitaRepository.save(receita);
    }

    @Transactional
    public void deletar(Long id) {
        Receita receita = buscarPorId(id);
        receitaRepository.delete(receita);
    }

    public List<Receita> buscarPorPeriodo(Date inicio, Date fim) {
        return receitaRepository.findByDataBetween(inicio, fim);
    }


}
