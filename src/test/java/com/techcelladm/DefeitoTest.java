package com.techcelladm;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefeitoTest {
    /**
   Verifica se o construtor da classe Defeito está funcionando corretamente.**/
    @Test
    public void testConstrutor(){
        Defeito defeito = new Defeito("Tela quebrada");
        assertEquals("Tela quebrada",defeito.getNome());
        assertEquals("",defeito.getDescricao());
    }

    /**
     Este teste verifica se o método setDescricao() da classe Defeito está funcionando corretamente. **/
    @Test
    public void testSetDescricao(){
        Defeito defeito = new Defeito("Tela quebrada");

        defeito.setDescricao("");

        assertEquals("", defeito.getDescricao());

    }

    /** Verifica se o método equals() da classe Defeito está retornando true quando dois defeitos são iguais.  **/
    @Test
    public void  testEqualsIguais(){
        Defeito defeito1 = new Defeito("Tela quebrada");
        Defeito defeito2 = new Defeito("Tela quebrada");
        assertTrue(defeito1.equals(defeito2));
    }

    /** verifica se o método equals() da classe Defeito está retornando false quando dois defeitos são diferentes.  **/
    @Test
    public void testEqualsDiferentes() {
        Defeito defeito1 = new Defeito("Tela quebrada");
        Defeito defeito2 = new Defeito("Tela rachada");

        assertFalse(defeito1.equals(defeito2));
    }
}