package com.techcelladm;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class ClienteTest {
    @Test
    public void testConstrutor() {
        Cliente cliente = new Cliente("João da Silva", "Rua da Paz, 123", "1234-5678", "joao@silva.com");

        assertEquals("João da Silva", cliente.getNome());
        assertEquals("Rua da Paz, 123", cliente.getEndereco());
        assertEquals("1234-5678", cliente.getTelefone());
        assertEquals("joao@silva.com", cliente.getEmail());
    }

    /** Este teste verifica se o método removerProduto() está removendo o produto corretamente da lista de produtos do cliente.**/
    @Test
    public void testRemoverProduto(){
        Cliente cliente = new Cliente("João da Silva","Rua da Paz", "4445-5455", "João@gmail.com");
        Produto produto = new Produto("iPhone 13 Pro", "Celular", "Apple", "A2641", 12999.99, 10);

        cliente.adicionarProduto(produto);
        cliente.removerProduto(produto);

        assertEquals(produto, cliente.getProdutos().get(0));
    }

}
