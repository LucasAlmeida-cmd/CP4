package com.example.ExercicioRevisao.repository;

import com.example.ExercicioRevisao.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
