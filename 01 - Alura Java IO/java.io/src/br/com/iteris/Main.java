package br.com.iteris;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        InputStream fis = new FileInputStream("lorem.txt");
        Reader isr = new InputStreamReader(fis,"UTF-8");
        BufferedReader br = new BufferedReader(isr);

        String linha = br.readLine();
        while (linha != null) {
            System.out.println(linha);
            linha = br.readLine();
        }

        br.close();

    }
}
