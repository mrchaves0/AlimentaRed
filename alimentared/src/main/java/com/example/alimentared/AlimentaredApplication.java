package com.example.alimentared;

import com.example.alimentared.Entity.CategoriaAlimento;
import com.example.alimentared.Repository.CategoriaAlimentoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AlimentaredApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlimentaredApplication.class, args);
	}

	@Bean
	CommandLineRunner initCategorias(CategoriaAlimentoRepository categoriaRepository) {
		return args -> {
			if (categoriaRepository.count() == 0) {
				categoriaRepository.saveAll(List.of(
						new CategoriaAlimento(null, "Panadería", "Pan, repostería y horneados"),
						new CategoriaAlimento(null, "Frutas y Verduras", "Frutas frescas y hortalizas"),
						new CategoriaAlimento(null, "Lácteos", "Leche, quesos y yogur"),
						new CategoriaAlimento(null, "Granos y Cereales", "Arroz, legumbres y cereales"),
						new CategoriaAlimento(null, "Comidas Preparadas", "Platos elaborados aptos para consumo")
				));
			}
		};
	}

}
