package br.com.bytebank.banco.test.io;

import br.com.bytebank.banco.modelo.Cliente;
import br.com.bytebank.banco.modelo.ContaCorrente;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TesteSerializacao {
    public static void main(String[] args) throws IOException {
        Cliente cliente = new Cliente();
        cliente.setNome("Raphael");
        cliente.setProfissao("Dev");
        cliente.setCpf("37555503859");

        ContaCorrente cc = new ContaCorrente(222, 333);
        cc.deposita(222.3);
        cc.setTitular(cliente);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cc.bin"));
        oos.writeObject(cc);
        oos.close();
    }
}
