package br.com.alura.bytebank.visualizador;
import br.com.alura.bytebank.io.LeitorCSV;
import br.com.alura.bytebank.model.Pagamento;
import br.com.alura.bytebank.servico.ServicoPagamento;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class OpcoesPagamento {

    static final Logger logger = Logger.getLogger(OpcoesPagamento.class);

    public void mensagemDeBoasVindas() {
        System.out.println("Bem vindo ao Byte Bank");
        leituraDeArquivo();
    }

    private void leituraDeArquivo() {
        System.out.println("Informe o código do arquivo que deseja registrar os pagamentos:");


        LeitorCSV leitor = new LeitorCSV();

        apresentaArquivosDisponiveis(leitor);


        try {
            int codigoDoArquivo = pedeCodigo();

            List<Pagamento> pagamentos = leitor.ler(codigoDoArquivo);
            ServicoPagamento registro = new ServicoPagamento();
            registro.registra(pagamentos);
        } catch (NoSuchFileException | NoSuchElementException e) {
            System.out.println("Arquivo não encontrado");
        } catch (IOException | NullPointerException e) {
            System.out.println("Erro na leitura do arquivo");
        }
    }

    private void apresentaArquivosDisponiveis(LeitorCSV leitor) {
        try {
            Iterator<Path> lista = leitor.lista();
            int i = 1;
            while (lista.hasNext()) {
                Path caminho = lista.next();
                System.out.println(i++ + " - " + caminho.getFileName().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int pedeCodigo() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }


}