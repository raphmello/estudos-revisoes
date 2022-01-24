package br.com.alura.bytebank.io;

import br.com.alura.bytebank.model.Pagamento;

import java.io.IOException;
import java.util.List;

public class LeitorXML extends Leitor implements LeitorPagamento {

    @Override
    public List<Pagamento> ler(int codigo) throws IOException {
        return null;
    }
}
