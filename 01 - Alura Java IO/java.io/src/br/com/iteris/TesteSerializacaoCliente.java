package br.com.iteris;

import java.io.*;

public class TesteSerializacaoCliente {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        Cliente cliente = new Cliente();
//        cliente.setNome("Raphael");
//        cliente.setProfissao("Dev");
//        cliente.setCpf("37555503859");
//
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("cliente.bin"));
//        oos.writeObject(cliente);
//        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("cliente.bin"));
        Cliente cliente = (Cliente) ois.readObject();
        ois.close();
        System.out.println(cliente.getCpf());
    }

}
