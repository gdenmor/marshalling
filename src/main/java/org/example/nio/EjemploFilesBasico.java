package org.example.nio;

import java.nio.file.*;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;

public class EjemploFilesBasico {
    public static void main(String[] args) {
        try {
            // 1. Crear un directorio
            Path directorio = Paths.get("mi_directorio");
            if (!Files.exists(directorio)) {
                Files.createDirectory(directorio);
                System.out.println("Directorio creado: " + directorio.toAbsolutePath());
            } else {
                System.out.println("El directorio ya existe: " + directorio.toAbsolutePath());
            }

            // 2. Crear un archivo dentro del directorio
            Path archivoTexto = directorio.resolve("archivo_texto.txt");
            List<String> lineas = Arrays.asList("Primera línea", "Segunda línea", "Tercera línea");
            Files.write(archivoTexto, lineas); // Escribir líneas de texto
            System.out.println("Archivo de texto creado: " + archivoTexto.toAbsolutePath());

            // 3. Copiar el archivo de texto
            Path copiaArchivoTexto = directorio.resolve("archivo_texto_copia.txt");
            Files.copy(archivoTexto, copiaArchivoTexto, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo de texto copiado a: " + copiaArchivoTexto.toAbsolutePath());

            // 4. Crear un archivo binario y escribir bytes
            Path archivoBinario = directorio.resolve("archivo_binario.bin");
            byte[] datosBinarios = {1, 2, 3, 4, 5, 6, 7, 8, 9};
            Files.write(archivoBinario, datosBinarios); // Escribir bytes
            System.out.println("Archivo binario creado y escrito: " + archivoBinario.toAbsolutePath());

            // 5. Copiar el archivo binario
            Path copiaArchivoBinario = directorio.resolve("archivo_binario_copia.bin");
            Files.copy(archivoBinario, copiaArchivoBinario, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo binario copiado a: " + copiaArchivoBinario.toAbsolutePath());

            // 6. Mover uno de los archivos copiados a otro subdirectorio
            Path subdirectorio = directorio.resolve("subdirectorio");
            if (!Files.exists(subdirectorio)) {
                Files.createDirectory(subdirectorio);
                System.out.println("Subdirectorio creado: " + subdirectorio.toAbsolutePath());
            }

            Path archivoMovido = subdirectorio.resolve(copiaArchivoTexto.getFileName());
            Files.move(copiaArchivoTexto, archivoMovido, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo movido a: " + archivoMovido.toAbsolutePath());

            // 7. Leer el contenido del archivo de texto
            List<String> lineasLeidas = Files.readAllLines(archivoTexto);
            System.out.println("Contenido del archivo de texto leído:");
            lineasLeidas.forEach(System.out::println);

            // 8. Leer el contenido del archivo binario
            byte[] datosLeidos = Files.readAllBytes(archivoBinario);
            System.out.println("Contenido del archivo binario leído: " + Arrays.toString(datosLeidos));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

