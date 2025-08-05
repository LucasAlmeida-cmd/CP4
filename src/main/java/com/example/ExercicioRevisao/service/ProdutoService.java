package com.example.ExercicioRevisao.service;

import com.example.ExercicioRevisao.exception.ProdutoNotFoundException;
import com.example.ExercicioRevisao.model.Produto;
import com.example.ExercicioRevisao.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;

    public Produto adicionarProduto(Produto produto){
        return produtoRepository.save(produto);
    }

    public Optional<Produto> getProduto(Long id){
        return produtoRepository.findById(id);
    }

    public List<Produto> getAllProdutos(){
        return produtoRepository.findAll();
    }

    public Produto atualizarProduto(Long id, Produto novoProduto) throws ProdutoNotFoundException {
        Produto produtoExistente = produtoRepository.findById(id)
                        .orElseThrow(() -> new ProdutoNotFoundException(id));

        produtoExistente.setNome(novoProduto.getNome());
        produtoExistente.setTipo(novoProduto.getTipo());
        produtoExistente.setClassificacao(novoProduto.getClassificacao());
        produtoExistente.setTamanho(novoProduto.getTamanho());
        produtoExistente.setPreco(novoProduto.getPreco());

        return produtoRepository.save(brinquedo);
    }

    public boolean deletarProduto(Long id){
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if(produtoExistente.isPresent()){
            produtoRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
