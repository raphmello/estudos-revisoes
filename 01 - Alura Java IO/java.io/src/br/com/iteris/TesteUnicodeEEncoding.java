package br.com.iteris;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class TesteUnicodeEEncoding {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "ç";
        System.out.println(s.codePointAt(0));

        Charset charset = Charset.defaultCharset();
        System.out.println(charset.displayName());

        byte[] bytes = s.getBytes("windows-1252");
        System.out.print(bytes.length + ", windows-1252, ");
        String s1 = new String(bytes, "windows-1252");
        System.out.println(s1);

        bytes = s.getBytes(StandardCharsets.UTF_16);
        System.out.print(bytes.length + ", UTF-16, ");
        s1 = new String(bytes, StandardCharsets.UTF_16);
        System.out.println(s1);

        bytes = s.getBytes(StandardCharsets.US_ASCII);
        System.out.print(bytes.length + ", ASCII, ");
        s1 = new String(bytes, StandardCharsets.US_ASCII);
        System.out.println(s1);
    }

}
