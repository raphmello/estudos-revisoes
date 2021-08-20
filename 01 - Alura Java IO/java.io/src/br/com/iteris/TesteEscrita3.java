package br.com.iteris;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class TesteEscrita3 {

    public static void main(String[] args) throws IOException {
//        OutputStream fos = new FileOutputStream("lorem2.txt");
//        Writer osw = new OutputStreamWriter(fos);
//        BufferedWriter bw = new BufferedWriter(osw);

//        BufferedWriter bw = new BufferedWriter(new FileWriter("lorem2.txt"));
//        PrintStream ps = new PrintStream("lorem2.txt");
        PrintWriter ps = new PrintWriter("lorem2.txt","UTF-8");

        ps.println("Lorem Ipsum is simply dummy text of the printing and typesetting industry.");
        ps.println();
        ps.println("Teste nova linhaç");

        ps.close();

        System.out.println();
    }

}
