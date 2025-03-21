package com.scaglia.controle_financeiro.services;

import com.scaglia.controle_financeiro.models.Despesa;
import com.scaglia.controle_financeiro.repositories.DespesaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    public Despesa criarDespesa(Despesa despesa) {
        return despesaRepository.save(despesa);
    }

    public List<Despesa> listarDespesas() {
        return despesaRepository.findAll();
    }

    public Optional<Despesa> buscarDespesaPorId(Long id) {
        return despesaRepository.findById(id);
    }

    public List<Despesa> buscarDespesasPorPeriodo(LocalDate inicio, LocalDate fim) {
        return despesaRepository.findByDataBetween(inicio, fim);
    }

    public void deletarDespesa(Long id) {
        despesaRepository.deleteById(id);
    }

    public Despesa editarDespesa(Long id, Despesa despesa) {
        Optional<Despesa> despesaExistente = despesaRepository.findById(id);
        if (despesaExistente.isPresent()) {
            Despesa despesaAtualizada = despesaExistente.get();
            despesaAtualizada.setDescricao(despesa.getDescricao());
            despesaAtualizada.setValor(despesa.getValor());
            despesaAtualizada.setData(despesa.getData());
            return despesaRepository.save(despesaAtualizada);
        }
        return null;
    }

    public void registrarPagamento(Long idDespesa) {
        Despesa despesa = despesaRepository.findById(idDespesa)
                .orElseThrow(() -> new RuntimeException("Despesa não encontrada"));

        // Atualizar o status da despesa para paga
        despesa.setPaga(true);
        despesa.setDataPagamento(LocalDate.now());

        // Salvar a despesa atualizada
        despesaRepository.save(despesa);
    }

}

