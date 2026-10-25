package com.example.projeto_dia_28;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LombokMapstruct {

	public static void main(String[] args) {
		SpringApplication.run(LombokMapstruct.class, args);
	}

}
