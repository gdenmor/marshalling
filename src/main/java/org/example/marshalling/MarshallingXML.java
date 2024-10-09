package org.example.marshalling;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;


public class MarshallingXML {
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
        String xmlFilePath = "usuario.xml";

        try {
            // Crear un contexto JAXB para la clase Usuario
            JAXBContext jaxbContext = JAXBContext.newInstance(Usuario.class);

            // Crear un marshaller para convertir el objeto a XML
            Marshaller marshaller = jaxbContext.createMarshaller();

            // Formatear el XML para que sea más legible
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // Serializar el objeto Usuario a una cadena XML
            StringWriter sw = new StringWriter();
            marshaller.marshal(usuario, sw);
            String xmlString = sw.toString();

            // Serializar el objeto Usuario a un fichero
            marshaller.marshal(usuario, new File(xmlFilePath));

            // Mostrar el XML resultante
            System.out.println("Objeto Usuario serializado a XML:");
            System.out.println(xmlString);


            // Deserializar objeto Usuario desde fichero XML
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            Usuario usuarioFromXml = (Usuario) unmarshaller.unmarshal(new File(xmlFilePath));

            // Mostramos el usuario recuperado del XML
            System.out.println("Objeto Usuario después de deserializar:");
            System.out.println(usuarioFromXml.toString());

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
