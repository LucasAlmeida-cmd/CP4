package com.example.ExercicioRevisao.controller;

import com.example.ExercicioRevisao.assembler.ProdutoAssembler;
import com.example.ExercicioRevisao.exception.ProdutoNotFoundException;
import com.example.ExercicioRevisao.model.Produto;
import com.example.ExercicioRevisao.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;
    @Autowired
    ProdutoAssembler produtoAssembler;

    @PostMapping
    public ResponseEntity<EntityModel<Produto>> adicionarProduto(@RequestBody Produto produto){
        Produto salvo = produtoService.adicionarProduto(produto);
        return ResponseEntity.ok(produtoAssembler.toModel(salvo));
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Produto>>> retornaTodos(){
        List<EntityModel<Produto>> produtos = produtoService.getAllProdutos()
                .stream()
                .map(produtoAssembler::toModel)
                .toList();
        return ResponseEntity.ok(CollectionModel.of(produtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Produto>> buscarPorId(@PathVariable Long id){
        return produtoService.getProduto(id)
                .map(produto -> ResponseEntity.ok(produtoAssembler.toModel(produto)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Produto>> atualizarPorId(@PathVariable Long id, @RequestBody Produto produto) {
        try {
            Produto atualizado = produtoService.atualizarProduto(id, produto);
            return ResponseEntity.ok(produtoAssembler.toModel(atualizado));
        } catch (ProdutoNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> deletarPorId(@PathVariable Long id){
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EntityModel<Produto>> atualizarParcialmente(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {
        try {
            Produto produtoAtualizado = produtoService.atualizarParcialmente(id, updates);
            return ResponseEntity.ok(produtoAssembler.toModel(produtoAtualizado));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (ProdutoNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
