package com.example.projeto_dia_28.mapper;

import com.example.projeto_dia_28.dto.LivroRequest;
import com.example.projeto_dia_28.dto.LivroResponse;
import com.example.projeto_dia_28.entity.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel="spring")
public interface LivroMapper {
    //Response -> Entity
    //@Mapping(source = "anoPublicacao", target = "anoPubli") quando quero mapear com nome diferente
    LivroResponse toResponse(Livro livro);

    //lista
    List<LivroResponse> toResponse(List<Livro> livro);

    //Request -> Entity
    //@Mapping(source = "anoPubli", target = "anoPublicacao") quando quero mapear com nome diferente
    Livro toEntity(LivroRequest livroRequest);
}
