package br.com.alura.rh.service.reajuste;

import br.com.alura.rh.ValidacaoException;
import br.com.alura.rh.model.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class ValidacaoPeriodicidadeEntreReajustes implements ValidacaoReajuste {

    @Override
    public void validar(Funcionario funcionario, BigDecimal aumento) {
        LocalDate dataUltimoReajuste = funcionario.getDataUltimoReajuste();
        LocalDate dataAtual = LocalDate.now();
        int months = Period.between(dataUltimoReajuste, dataAtual).getMonths();
        if(months < 6) {
            throw new ValidacaoException("Intervalo deve ser de no mínimo 6 meses!");
        }
    }
}
