package com.example.projeto_dia_28.repositoy;


import com.example.projeto_dia_28.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro,Integer> {
}
