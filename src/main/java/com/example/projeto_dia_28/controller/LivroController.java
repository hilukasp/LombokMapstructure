package com.example.projeto_dia_28.controller;

import com.example.projeto_dia_28.dto.LivroRequest;
import com.example.projeto_dia_28.dto.LivroResponse;
import com.example.projeto_dia_28.entity.Livro;
import com.example.projeto_dia_28.mapper.LivroMapper;
import com.example.projeto_dia_28.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController {
    private final LivroService livroService;
    private final LivroMapper livroMapper;

    @GetMapping("/id")
    public ResponseEntity<LivroResponse> buscar(
            @PathVariable Integer id){
        Livro livro = livroService.buscarLivroPorId(id);
        LivroResponse dto = livroMapper.toResponse(livro);
        return ResponseEntity.ok(dto);
    }

    @GetMapping()
    public ResponseEntity<Page<LivroResponse>> listar(@RequestParam(required = false,defaultValue = "0") int page,
                                                      @RequestParam(required = false,defaultValue = "10") int size){
        PageRequest paginacao=PageRequest.of(page,size);

        Page<Livro> livros=livroService.listarLivro(paginacao);

        Page<LivroResponse> dtos = livros.map(livroMapper::toResponse);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping()
    public ResponseEntity<LivroResponse> criar(@RequestBody LivroRequest livroRequest){
        Livro livroSalvo=livroMapper.toEntity(livroRequest);
        Livro livro=livroService.criarLivro(livroSalvo);
        LivroResponse livroresponse=livroMapper.toResponse(livro);
        return ResponseEntity.ok(livroresponse);
    }

}
