package com.example.ExercicioRevisao.exception;

public class ProdutoNotFoundException extends Exception {
    public ProdutoNotFoundException(Long id){
        super("Produto de ID " + id + " não foi encontrado.");
    }
}
