package com.techcelladm;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private List<Produto> produto;

    public Cliente(String nome, String endereco, String telefone, String email){
        this.nome = nome;
        this.endereco = endereco;
        this.telefone =  telefone;
        this.email = email;
        this.produto = new ArrayList<>();
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getEndereco(){
        return endereco;
    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public List<Produto> getProdutos(){
        return produto;
    }

    public void setProdutos(List<Produto> produto){
        this.produto = produto;
    }

    public void adicionarProduto(Produto produto){
        this.produto.add(produto);
    }

    public void removerProduto(Produto produto){
        this.produto.remove(produto);
    }


}
