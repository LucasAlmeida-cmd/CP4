package com.example.ExercicioRevisao.assembler;

import com.example.ExercicioRevisao.controller.ProdutoController;
import com.example.ExercicioRevisao.model.Produto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProdutoAssembler implements RepresentationModelAssembler<Produto, EntityModel<Produto>> {

    @Override
    public EntityModel<Produto> toModel(Produto produto) {
        return EntityModel.of(produto,
                linkTo(methodOn(ProdutoController.class).buscarPorId(produto.getId())).withSelfRel(),
                linkTo(methodOn(ProdutoController.class).adicionarProduto(produto)).withRel("adicionar"),
                linkTo(methodOn(ProdutoController.class).retornaTodos()).withRel("retornarTodos"),
                linkTo(methodOn(ProdutoController.class).atualizarPorId(produto.getId(), produto)).withRel("atualizarProdutoPorId"),
                linkTo(methodOn(ProdutoController.class).deletarPorId(produto.getId())).withRel("deletarProdutoPorId")
        );
    }
}

