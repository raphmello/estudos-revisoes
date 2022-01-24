package br.com.alura.tdd.service;

import br.com.alura.tdd.modelo.Funcionario;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BonusServiceTest {

    @Test
    void bonusDeveriaSerZeroParaFuncionarioComSalarioMuitoAlto() {
        BonusService service = new BonusService();
        //assertThrows(IllegalArgumentException.class,() -> service.calcularBonus(new Funcionario("Raphael", LocalDate.now(), new BigDecimal("11000"))));

        try {
            service.calcularBonus(new Funcionario("Raphael", LocalDate.now(), new BigDecimal("10001")));
            fail("Não aconteceu a exception");
        } catch (Exception e) {
            assertEquals("Funcionário com salário maior que R$10000 reais não pode receber bônus",e.getMessage());
        }
    }

    @Test
    void bonusDeveriaSerDezPorCentoDoSalario() {
        BonusService service = new BonusService();
        BigDecimal bonus = service.calcularBonus(new Funcionario("Raphael", LocalDate.now(), new BigDecimal("2500")));

        assertEquals(new BigDecimal("250.00"),bonus.setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void bonusDeveriaSerDezPorCentoParaSalarioDeExatamente10000() {
        BonusService service = new BonusService();
        BigDecimal bonus = service.calcularBonus(new Funcionario("Raphael", LocalDate.now(), new BigDecimal("10000")));

        assertEquals(new BigDecimal("1000.00"),bonus.setScale(2, RoundingMode.HALF_UP));
    }
}
