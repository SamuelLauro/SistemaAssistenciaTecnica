package com.techcelladm;

import java.util.List;

public class OrdemDeServico {
    private Cliente cliente;
    private Produto produto;
    private Servico servico;
    private Defeito defeito;
    private List <Peca> peca;

    public OrdemDeServico(Cliente cliente, Produto produto, Servico servico, Defeito defeito, List<Peca>peca){
        this.cliente = cliente;
        this.produto = produto;
        this.servico = servico;
        this.defeito = defeito;
        this.peca = peca;
    }

    public Cliente getCliente(){
        return cliente;
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    
    public Produto getProduto(){
        return produto;
    }

    public void setProduto(Produto produto){
        this.produto = produto;
    }

    public Servico getServico(){
        return servico;
    }

    public void setServico(Servico servico){
        this.servico = servico;
    }

    public Defeito getDefeito(){
        return defeito;
    }

    public void setDefeito(Defeito defeito){
        this.defeito = defeito;
    }

    public List<Peca> getPeca(){
        return peca;
    }

    public void setPeca(List<Peca> peca){
        this.peca = peca;
    }
}
