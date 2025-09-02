package com.example.ExercicioRevisao.service;

import com.example.ExercicioRevisao.exception.ProdutoNotFoundException;
import com.example.ExercicioRevisao.model.Produto;
import com.example.ExercicioRevisao.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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

        return produtoRepository.save(produtoExistente);
    }

    public boolean deletarProduto(Long id){
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if(produtoExistente.isPresent()){
            produtoRepository.deleteById(id);
            return true;
        }

        return false;
    }

    public Produto atualizarParcialmente(Long id, Map<String, Object> updates) throws ProdutoNotFoundException {
        return produtoRepository.findById(id)
                .map(produto -> {
                    updates.forEach((key, value) -> {
                        switch (key) {
                            case "nome":
                                produto.setNome((String) value);
                                break;
                            case "tipo":
                                produto.setTipo((String) value);
                                break;
                            case "classificacao":
                                produto.setClassificacao((String) value);
                                break;
                            case "tamanho":
                                produto.setTamanho(convertToDouble(value));
                                break;
                            case "preco":
                                produto.setPreco(convertToDouble(value));
                                break;
                            default:
                                throw new IllegalArgumentException("Campo inválido: " + key);
                        }
                    });
                    return produtoRepository.save(produto);
                })
                .orElseThrow(() -> new ProdutoNotFoundException(id));
    }

    private Double convertToDouble(Object value) {
        if (value instanceof Number) {
            return ((Number) value).doubleValue();
        } else if (value instanceof String) {
            try {
                return Double.parseDouble((String) value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Valor inválido para número: " + value);
            }
        }
        throw new IllegalArgumentException("Tipo de valor não suportado para conversão: " + value.getClass());
    }

    public Produto findAllByNome(String nome) {
        return produtoRepository.findAllByNome(nome);
    }

    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }
}
