package br.com.alura.bytebank.io;

import br.com.alura.bytebank.model.Pagamento;

import java.io.IOException;
import java.util.List;

public interface LeitorPagamento {
    List<Pagamento> ler(int codigo) throws IOException;
}
