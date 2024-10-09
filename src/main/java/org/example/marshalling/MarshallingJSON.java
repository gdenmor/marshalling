package org.example.marshalling;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MarshallingJSON {

    public static void main(String[] args) {
        // Crear un objeto Usuario
        Usuario usuario = new Usuario("1","Juan","Moral","jmoral","1234");

        // Le metemoos roles al Usuario
        ArrayList<String> rolesUsuario = new ArrayList<String>();
        rolesUsuario.add("Administrador");
        rolesUsuario.add("Encargado");
        rolesUsuario.add("Jefe");

        usuario.setRoles(rolesUsuario);

        // Fichero para serializar el objeto Usuario
        String jsonFilePath = "usuario.json";

        try {

            //Creamos un objeto que utiliza la biblioteca Jackson para convertir objetos Java a formatos como JSON
            ObjectMapper objectMapper = new ObjectMapper();


            // Serializar el objeto Usuario a una cadena JSON
            String jsonString = objectMapper.writeValueAsString(usuario);

            // Mostrar el JSON resultante
            System.out.println("Objeto Usuario serializado a JSON:");
            System.out.println(jsonString);

            // Serializar el objeto Usuario a un fichero JSON
            objectMapper.writeValue(new File(jsonFilePath), usuario);


            // Deserializar el JSON a un objeto Usuario
            Usuario usuarioFromXml = objectMapper.readValue(new File(jsonFilePath), Usuario.class);

            // Mostramos el usuario recuperado del XML
            System.out.println("Objeto Usuario después de deserializar:");
            System.out.println(usuarioFromXml.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
