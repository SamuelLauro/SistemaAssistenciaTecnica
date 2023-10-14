package com.techcelladm;

public class Produto {
    String nome;
    String descricao;
    String marca;
    String modelo;
    String numeroDeSerie;
    Float preco;
    Integer estoque;

    public Produto(String string, String string2, String string3, String string4, double d, int i) {
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public String getMarca(){
        return marca;
    }

    public void setMarca(String marca){
        this.marca = marca;
    }

    public String getNumeroDeSerie(){
        return numeroDeSerie;
    }

    public void setNumeroDeSerie(String numeroDeSerie){
        this.numeroDeSerie = numeroDeSerie;
    }

    public Float getPreco(){
        return preco;
    }

    public void setPreco(Float preco){
        this.preco = preco;
    }

    public Integer getEstoque(){
        return estoque;
    }

    public void setEstoque(Integer estoque){
        this.estoque = estoque;
    }
}
