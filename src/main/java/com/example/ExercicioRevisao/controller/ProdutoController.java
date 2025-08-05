package com.example.ExercicioRevisao.controller;

import com.example.ExercicioRevisao.exception.ProdutoNotFoundException;
import com.example.ExercicioRevisao.model.Produto;
import com.example.ExercicioRevisao.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> adicionarProduto(@RequestBody Produto produto){
        Produto produto1 = produtoService.adicionarProduto(produto);
        return  ResponseEntity.ok(produto1);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> retornaTodos(){
        return ResponseEntity.ok(produtoService.getAllProdutos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id){
        return produtoService.getProduto(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarPorId(@PathVariable Long id, @RequestBody Produto brinquedoNovo) {
        try {
            Produto atualizado = produtoService.atualizarProduto(id, brinquedoNovo);
            return ResponseEntity.ok(atualizado);
        } catch (ProdutoNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> deletarPorId(@PathVariable Long id){
        try {
            produtoService.deletarProduto(id);
            return ResponseEntity.noContent().build();
        } catch (ProdutoNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
