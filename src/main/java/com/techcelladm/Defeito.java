package com.techcelladm;

public class Defeito {
    private String nome;
    private String descricao;

    public Defeito(String nome){
        this.nome = nome;
        this.descricao = "";
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getDescricao(){
        if (descricao == null){
            return "";
        } else{
            return descricao;
        }
    }

    public void setDescricao(String descricao){
        if (descricao == null){
            descricao = "";
        }
        this.descricao = descricao;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Defeito outroDefeito = (Defeito) obj;
        return nome.equals(outroDefeito.nome) && descricao.equals(outroDefeito.descricao);
    }
}
