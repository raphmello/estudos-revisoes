package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProdutoTest {

    @Test
    public void verificaPrecoComImposto() {
        Produto bala = new Produto("bala",0.10);
        assertEquals(0.11,bala.getPrecoComImpostos(), 0.00001);
    }
}
