package org.example.practica;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Metodos {
    /**
     * Metodo que obtiene las casetas del archivo .txt en el cual separa las
     * diferentes partes de la línea para poder obtener los objetos de las diferentes casetas
     * @return devuelve un arrayList con las diferentes casetas existentes en el archivo
     */
    public static ArrayList<CasetaFeria> obtenCasetas(){
        /*
            Primero trataremos de buscar el directorio donde se encuentra el archivo
            .txt con las diferentes casetas existentes
         */
        Path directorio = Paths.get("C:/Users/aula18/IdeaProjects/xml");
        Path archivoTexto = directorio.resolve("casetas.txt");
        ArrayList<CasetaFeria> casetas=new ArrayList<>();
        try {
            // Lee las líneas del archivo .txt
            List<String> lineasLeidas = Files.readAllLines(archivoTexto);
            int i=0;
            //Leeremos línea a línea el archivo para crear las diferentes casetas
            for (String linea : lineasLeidas) {
                i++;
                //Separamos en un array las diferentes partes que tiene cada caseta
                String[] partes = linea.split("-");
                /*
                    Crearemos cada caseta con ese array con los datos y usaremos el trim
                    para eliminar los espacios
                 */
                casetas.add(new CasetaFeria(i,partes[0].trim(),partes[1].trim(),Integer.parseInt(partes[2].trim()),partes[3].trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return casetas;
    }



    /**
     * Método que las casetas que existen las añade al archivo XML
     * @param casetas le pasaremos el arraylist de las casetas que existen en el archivo
     */
    public static void MarshallingaXML(ArrayList<CasetaFeria> casetas){
        try{
            // Creamos un contexto JAXB para la clase Casetas
            JAXBContext jaxbContext = JAXBContext.newInstance(Casetas.class);

            // Creamos un marshaller para convertir el objeto a XML
            Marshaller marshaller = jaxbContext.createMarshaller();

            // Formatear el XML para que sea más legible
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Buscaremos el archivo casetas.xml y en caso de no existir lo crea
            File file=new File("casetas.xml");
            Casetas casetass=new Casetas(casetas);
            StringWriter sw = new StringWriter();
            //añadimos al archivo las diferentes casetas
            marshaller.marshal(casetass, file);
        }catch (JAXBException e){
            e.printStackTrace();
        }

    }



    /**
     * En este método añadiremos las casetas a un archivo json para guardarlas ahí
     * @param casetaFerias le pasaremos el arraylist de las casetas existentes
     */
    public static void MarshallingaJSON(ArrayList<CasetaFeria>casetaFerias){
        try {
            Casetas casetass=new Casetas(casetaFerias);
            //Creamos un objeto que utiliza la biblioteca Jackson para convertir objetos Java a formatos como JSON
            ObjectMapper objectMapper = new ObjectMapper();

            // Serializar el objeto Casetas a un fichero JSON
            objectMapper.writeValue(new File("casetas.json"), casetass);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Método en el cual obtendremos las casetas existentes en el archivo XML
     */
    public static void UnmarshallingaXML(){
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(Casetas.class);
            // Deserializar objeto Casetas desde fichero XML
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            //Obtenemos todas las casetas existentes en el archivo XML
            Casetas casetas = (Casetas) unmarshaller.unmarshal(new File("casetas.xml"));
            ArrayList<CasetaFeria> casetaas=casetas.getCasetas();
            for (CasetaFeria caseta : casetaas) {
                System.out.println(caseta);
            }
        }catch (JAXBException e){
            e.printStackTrace();
        }
    }



    /**
     * Método en el cual obtebdremos las casetas existentes en el archivo JSON
     */
    public static void UnMarshallingJson(){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            //Obtenemos las casetas del archivo JSON
            Casetas casetas = objectMapper.readValue(new File("casetas.json"), Casetas.class);
            for (CasetaFeria caseta : casetas.getCasetas()) {
                System.out.println(caseta);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }



    /**
     * Método en el cual buscaremos una caseta por su id
     * y en caso de no encontrarse mostrará un mensaje diciendo que la caseta
     * no ha sido encontrada según el id introducido por el usuario en el archivo XML
     * @param id Le pasaremos el id de la caseta
     */
    public static void SearchByIdJSON(int id) {
        try{
            boolean encontrado=true;
            ObjectMapper objectMapper = new ObjectMapper();
            //Obtendremos las casetas del archivo JSON
            Casetas casetas = objectMapper.readValue(new File("casetas.json"), Casetas.class);
            for (CasetaFeria caseta : casetas.getCasetas()) {
                /*
                    Si existe una caseta con ese id lo imprime y sino lo pone
                    como no encontrado
                 */
                if (caseta.getId() == id) {
                    System.out.println(caseta);
                }else{
                    encontrado=false;
                }
            }

            //Si no se encuentra le dice al usuario que esa caseta no ha sido encontrada
            if (!encontrado){
                System.out.println("Caseta no encontrada");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }





    /**
     * Método en el cual buscaremos una caseta por su id
     * y en caso de no encontrarse mostrará un mensaje diciendo que la caseta
     * no ha sido encontrada según el id introducido por el usuario en el archivo JSON
     * @param id Le pasaremos el id de la caseta
     */
    public static void SearchByIdXML(int id){
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(Casetas.class);
            // Deserializar objeto Usuario desde fichero XML
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            boolean encontrado=true;

            //Obtendremos las casetas del archivo XML
            Casetas casetas = (Casetas) unmarshaller.unmarshal(new File("casetas.xml"));
            ArrayList<CasetaFeria> casetaas=casetas.getCasetas();
            for (CasetaFeria caseta : casetaas) {
                /*
                    Si existe una caseta con ese id lo imprime y si no lo pone
                    como no encontrado
                 */
                if (caseta.getId() == id) {
                    System.out.println(caseta);
                }else{
                    encontrado=false;
                }
            }
            //Si no se encuentra le dice al usuario que esa caseta no ha sido encontrada
            if (!encontrado){
                System.out.println("Caseta no encontrada");
            }
        }catch (JAXBException e){
            e.printStackTrace();
        }
    }



    /**
     * Menú que se mostrará por pantalla para que el usuario elija
     * una de las opciones del menú
     */
    public static void Menu(){
        //Haremos aquí un mínimo diseño en el menú para que sea claro para el usuario
        System.out.println("=============================");
        System.out.println("MENÚ");
        System.out.println("=============================");
        System.out.println("Elija una opción");
        System.out.println("==========================");
        System.out.println("1- Marshalling casetas a XML");
        System.out.println("----------------------------------");
        System.out.println("2- Unmarshalling casetas de XML");
        System.out.println("----------------------------------");
        System.out.println("3- Mostrar la caseta número X desde XML");
        System.out.println("----------------------------------");
        System.out.println("4- Marshalling casetas a JSON");
        System.out.println("----------------------------------");
        System.out.println("5- Unmarshalling casetas de JSON");
        System.out.println("----------------------------------");
        System.out.println("6- Mostrar la caseta número X desde JSON");
        System.out.println("----------------------------------");
        System.out.println("7- Salir");
    }





    /**
     * Método principal que es el que se encargará de llamar
     * al resto de los métodos para que el programa funcione
     * correctamente
     */
    public static void Main(){
        Scanner sc= new Scanner(System.in);
        int opc=0;
        //Bucle hasta que pulsemos en Salir que se saldrá del bucle y finalizará el programa
        do{
            //Llamamos al método que mostrará el menú
            Menu();
            //Escogemos la opción del menú correspondiente
            opc=sc.nextInt();
            /*
                Este método gestiona las diferentes opciones dependiendo cual haya
                elegido el usuario
             */
            Opciones(opc,sc);
        }while (opc!=7);
    }





    /**
     * En este método tendremos las diferentes funcionalidades que tendrá el programa
     * dependiendo de la opcion que elijas
     * @param opc opción elegida
     * @param sc el objeto scanner para leer los datos que sean necesarios
     */
    public static void Opciones(int opc,Scanner sc){
        //Primero llamamos al método que nos devuelven las casetas existentes en el archivo
        ArrayList<CasetaFeria>casetas=Metodos.obtenCasetas();
        //A continuación, veremos que haremos dependiendo de la opción elegida
        switch (opc){
            //Código en caso de elegir la opción 1
            case 1:
                //Llamamos al método que meterá las casetas en el archivo XML
                //Si este existe los sobreescribe
                Metodos.MarshallingaXML(casetas);
                break;
            //Código en caso de elegir la opción 2
            case 2:
                //Aquí primero comprobaremos si el archivo XML existe
                if (new File("casetas.xml").exists()){
                    //Si existe, llamaremos al método que obtiene las casetas del archivo XML
                    Metodos.UnmarshallingaXML();
                }else{
                    /*
                        Sino existe, saldrá un mensaje que diga que no puedes obtener las casetas
                        porque el archivo no existe
                     */
                    System.out.println("No podemos sacar las casetas porque el archivo no existe");
                }
                break;
            //Código en caso de elegir la opción 3
            case 3:
                //Aquí primero comprobaremos si el archivo XML existe
                if (new File("casetas.xml").exists()){
                    //Si existe, llamaremos al método que obtiene la caseta según el ID introducido
                    System.out.println("Introduce el id de la caseta que desea ver");
                    int idcaseta = sc.nextInt();
                    Metodos.SearchByIdXML(idcaseta);
                }else{
                    /*
                        Si no existe, saldrá un mensaje que diga que no puedes obtener las casetas
                        porque el archivo no existe
                     */
                    System.out.println("No podemos sacar las casetas porque el archivo no existe");
                }
                break;
            //Código en caso de elegir la opción 4
            case 4:
                //Llamaremos al método que mete las casetas en el archivo JSON
                Metodos.MarshallingaJSON(casetas);
                break;
            //Código en caso de elegir la opción 5
            case 5:
                //Aquí primero comprobaremos si el archivo JSON existe
                if (new File("casetas.json").exists()){
                    //Si existe, llamaremos al método que obtiene las casetas del archivo JSON
                    Metodos.UnMarshallingJson();
                }else{
                     /*
                        Si no existe, saldrá un mensaje que diga que no puedes obtener las casetas
                        porque el archivo no existe
                     */
                    System.out.println("No podemos sacar las casetas porque el archivo no existe");
                }
                break;
            //Código en caso de elegir la opción 6
            case 6:
                //Aquí primero comprobaremos si el archivo JSON existe
                if (new File("casetas.json").exists()){
                    //Si existe, llamaremos al método que obtiene la caseta según el ID introducido
                    System.out.println("Introduce el id de la caseta que desea ver");
                    int id = sc.nextInt();
                    Metodos.SearchByIdJSON(id);
                }else{
                    /*
                        Si no existe, saldrá un mensaje que diga que no puedes obtener las casetas
                        porque el archivo no existe
                     */
                    System.out.println("No podemos sacar las casetas porque el archivo no existe");
                }
                break;
        }
    }
}
