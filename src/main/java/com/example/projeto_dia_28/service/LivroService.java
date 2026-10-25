
package com.example.projeto_dia_28.service;

import com.example.projeto_dia_28.entity.Livro;
import com.example.projeto_dia_28.repositoy.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;

    @Cacheable(value = "livros", key = "'pagina:' + #pageable.pageNumber + ':' + #pageable.pageSize")
    public Page<Livro> listarLivro(Pageable pageable) {
        System.out.println("Listando livros de pagina: " + pageable.getPageNumber());
        return livroRepository.findAll(pageable);
    }

    @Cacheable(value = "livros", key = "#id")
    public Livro buscarLivroPorId(Integer id) {
        System.out.println("Buscando livro no banco");

        return livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Livro não encontrado"
                ));
    }

    @CachePut(value = "livros", key = "#result.id")
    public Livro criarLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    @CachePut(value = "livros", key = "#id")
    public Livro atualizarLivro(Integer id, Livro livroAtualizado) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Livro não encontrado"
                ));

        livro.setTitulo(livroAtualizado.getTitulo());
        livro.setAutor(livroAtualizado.getAutor());
        livro.setAnoPublicacao(livroAtualizado.getAnoPublicacao());

        return livroRepository.save(livro);
    }

    @CacheEvict(value = "livros", key = "#id")
    public void deletarPorId(Integer id) {
        if (!livroRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Livro não encontrado"
            );
        }

        livroRepository.deleteById(id);
    }
}
