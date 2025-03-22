package com.scaglia.controle_financeiro.services;

import com.scaglia.controle_financeiro.exception.ResourceNotFoundException;
import com.scaglia.controle_financeiro.models.Categoria;
import com.scaglia.controle_financeiro.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria salvarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public Categoria atualizarCategoria(Long id, Categoria categoria) {
        if (categoriaRepository.existsById(id)) {
            categoria.setId(id);
            return categoriaRepository.save(categoria);
        }
        throw new ResourceNotFoundException("Categoria com ID " + id + " não encontrada.");
    }

    public void deletarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }
}

