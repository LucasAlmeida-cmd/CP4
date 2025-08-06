package com.example.ExercicioRevisao.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombock, @Data cria toda a contrução da classe
@Entity
@Table(name = "TDS_TB_FERRAMENTAS")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String tipo;
    private String classificacao;
    private double tamanho;
    private double preco;
}