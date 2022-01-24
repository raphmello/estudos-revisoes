package br.com.alura.bytebank.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

public class Leitor {
    public static final String RECURSOS = "recursos";
    public static final String BACKUP = "backup";

    protected void geraBackup(Path arquivo) throws IOException {
        Path backup = Paths.get(BACKUP);
        if (!Files.isDirectory(backup)) {
            Files.createDirectory(backup);
        }

        Path nomeDoArquivo = arquivo.getFileName();

        String arquivoAbsoluto = System.currentTimeMillis() + "-" + nomeDoArquivo.toString();

        Path origem = Paths.get(arquivo.toString());
        Path destino = Paths.get(backup.toString(), arquivoAbsoluto);

        Files.copy(origem, destino);

    }

    protected String devolveArquivo(int codigo) throws IOException {
        Iterator<Path> arquivos = lista();
        int i = 0;

        Path path = null;

        while (i < codigo) {
            path = arquivos.next();
            i++;
        }

        return path.toString();
    }

    public Iterator<Path> lista() throws IOException {
        return Files.newDirectoryStream(Paths.get(RECURSOS)).iterator();
    }
}
