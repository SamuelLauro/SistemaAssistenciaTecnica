package com.techcelladm;
import static org.junit.Assert.assertEquals;

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

    @Test
    public void testAdicionarProduto() {
        Cliente cliente = new Cliente("João da Silva", "Rua da Paz, 123", "1234-5678", "joao@silva.com");
        Produto produto = new Produto("iPhone 13 Pro", "Celular", "Apple", "A2641", 12999.99, 10);

        cliente.adicionarProduto(produto);

        assertEquals(1, cliente.getProdutos().size());
        assertEquals("iPhone 13 Pro", cliente.getProdutos().get(0).getNome());
    }

}
