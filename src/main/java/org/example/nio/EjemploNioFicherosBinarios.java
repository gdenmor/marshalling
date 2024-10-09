package org.example.nio;

import java.nio.file.*;
import java.io.IOException;
import java.util.Arrays;

public class EjemploNioFicherosBinarios {
    public static void main(String[] args) {
        try {
            // 1. Definir la ruta del archivo binario
            Path archivoBinario = Paths.get("datos.bin");

            // 2. Crear un array de bytes (datos binarios) que queremos escribir
            byte[] datosBinarios = new byte[] { 10, 20, 30, 40, 50, 60, 70, 80, 90 };

            // 3. Escribir el array de bytes en el archivo binario
            Files.write(archivoBinario, datosBinarios);
            System.out.println("Datos binarios escritos en: " + archivoBinario.toAbsolutePath());

            // 4. Leer el contenido del archivo binario
            byte[] datosLeidos = Files.readAllBytes(archivoBinario);
            System.out.println("Datos leídos del archivo binario: " + Arrays.toString(datosLeidos));

            // 5. Copiar el archivo binario a otra ubicación
            Path copiaArchivoBinario = Paths.get("copia_datos.bin");
            Files.copy(archivoBinario, copiaArchivoBinario, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo binario copiado a: " + copiaArchivoBinario.toAbsolutePath());

            // 6. Mover el archivo copiado a un subdirectorio
            Path subdirectorio = Paths.get("binarios");
            if (!Files.exists(subdirectorio)) {
                Files.createDirectory(subdirectorio);
                System.out.println("Subdirectorio creado: " + subdirectorio.toAbsolutePath());
            }

            Path archivoMovido = subdirectorio.resolve(copiaArchivoBinario.getFileName());
            Files.move(copiaArchivoBinario, archivoMovido, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo copiado movido a: " + archivoMovido.toAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
