package com.example.projeto_dia_28.dto;

import lombok.Data;

@Data
public class LivroResponse {
    private Integer id;
    private String titulo;
    private String autor;
    private String anoPublicacao;
}
