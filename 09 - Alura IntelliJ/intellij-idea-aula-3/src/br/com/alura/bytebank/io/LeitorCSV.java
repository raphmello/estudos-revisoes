package br.com.alura.bytebank.io;

import br.com.alura.bytebank.model.Pagamento;
import br.com.alura.bytebank.model.Tipo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LeitorCSV extends Leitor implements LeitorPagamento {

    @Override
    public List<Pagamento> ler(int codigo) throws IOException {
        String arquivo = devolveArquivo(codigo);

        ArrayList<Pagamento> pagamentos = new ArrayList<>();

        Path caminho = Paths.get(arquivo);

        List<String> linhas = Files.readAllLines(caminho, Charset.forName("utf-8"));

        linhas.forEach(linha -> {
            String[] palavras = linha.split(",");
            if (!linha.isEmpty()) {
                Double valor = Double.valueOf(palavras[0]);
                String descricao = palavras[1];
                Tipo tipo = Tipo.valueOf(palavras[2]);
                Pagamento pagamento = new Pagamento(tipo, valor, descricao);
                pagamentos.add(pagamento);
            }
        });

        geraBackup(caminho);
        return pagamentos;
    }

}
