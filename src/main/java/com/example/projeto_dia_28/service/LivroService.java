package com.example.projeto_dia_28.service;

import com.example.projeto_dia_28.entity.Livro;
import com.example.projeto_dia_28.repositoy.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.data.autoconfigure.web.DataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LivroService {
    private final LivroRepository livroRepository;

    public Page<Livro> listarLivro(Pageable pageable) {
        return livroRepository.findAll(pageable);
    }

    public Livro buscarLivroPorId(Integer id){
        if (livroRepository.existsById(id)){
            Optional<Livro> livro = livroRepository.findById(id);
            return livro.get();
        }
        return null;
    }

    public Livro criarLivro(Livro livro)
    {
        return livroRepository.save(livro);
    }


    public void deletarPorId(Integer id){
        if (!livroRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        livroRepository.deleteById(id);
    }
}
