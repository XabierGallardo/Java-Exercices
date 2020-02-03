package tarefa12.ii;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import org.apache.commons.dbcp2.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Attr;

public class Tarefa12II {
    
    //We create the map to store new Podcast
    public static HashMap<Integer, Podcast> listaPodcasts = new HashMap<Integer, Podcast>();

    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {
        Scanner teclado = new Scanner(System.in);

        int opcion = 0;
        while (opcion != 7) {
            
            //First, create DB connection, therefore, open a DataSource and input data
            try { 
                BasicDataSource datos = new BasicDataSource();
                datos.setDriverClassName("com.mysql.jdbc.Driver");
                datos.setUsername("root");
                datos.setPassword("root");
                datos.setUrl("jdbc:mysql://127.0.0.1:3306/podcast_bd");

                Connection con = datos.getConnection();                
                //Launch this method to avoid more DB consulting
                lanzarPodcast(con);

                System.out.println("=================================================================");
                System.out.println("Benvido ó canal de podcast do San Clemente!");
                System.out.println("Prema no número correspondente para realizar a acción desexada:");
                System.out.println("=================================================================");
                System.out.println("Agregar novo podcast.....................................1");
                System.out.println("Dar de alta novo xénero..................................2");
                System.out.println("Actualizar xéneros dun podcast...........................3");
                System.out.println("Eliminar podcast.........................................4");
                System.out.println("Visualizar os podcast da BD..............................5");
                System.out.println("Visualizar un podcast e xenerar XML......................6");
                System.out.println("Saír.....................................................7");
                System.out.println("==================================================================");

                opcion = teclado.nextInt();
                teclado.nextLine();
                switch (opcion) {
           
                    case 1: //Add new Podcast
                        
                        System.out.println("Introduzca a continuación el título del podcast:");
                        String titulo = teclado.nextLine();
                        System.out.println("Introduzca la cantidad de minutos de duración del podcast:  ");
                        int duracion = teclado.nextInt();
                        teclado.nextLine();
                        System.out.println("Introduzca la periocidad del podcast: ");
                        String periocidad = teclado.nextLine();
                        
                        String nombreAutor = "";
                        System.out.println("Elija entre los siguientes al autor del podcast: ");
                        Statement senten = con.createStatement();
                        ResultSet resultado = senten.executeQuery("SELECT idAutor, nombre, apellidos FROM autor");
                        
                        //While we're obtaining the data, we pick each data from the query
                        while (resultado.next()) {
                            String code = resultado.getString("idAutor");
                            String nombre = resultado.getString("nombre");
                            String apellidos = resultado.getString("apellidos");
                            System.out.println(code + ". " + nombre + ", " + apellidos);
                        }
                        
                        int autor = Integer.parseInt(teclado.nextLine());//Pick autor id and parse it

                        System.out.println("Introduzca a continuación el tipo de podcast: ");
                        System.out.println("Podcast musical.....0");
                        System.out.println("Podcast de vídeo....1");
                        int tipo = Integer.parseInt(teclado.nextLine());

                        //Execute SQL sentence to add a podcast to the max number and add a id
                        ResultSet rsIdPodcast = senten.executeQuery("select max(idPodcast) + 1 as proximoId from Podcast");
                        int idPodcast = 0;
                        if (rsIdPodcast.next()) {
                            idPodcast = Integer.parseInt(rsIdPodcast.getString("proximoId"));
                        }

                        //Classify according to the option
                        if (tipo == 0) {
                            System.out.println("Indique en kbps la calidad del archivo: ");
                            String calidad = teclado.nextLine();
                            senten.execute("INSERT INTO Podcast" + " (idPodcast,titulo,tipo,calidad,duracion,periocidad,autor)" + " VALUES ('" + idPodcast + "','" + titulo + "','" + tipo + "','" + calidad + "','" + duracion + "','" + periocidad + "','" + autor + "')");

                            //Insert values onto listmap of podcast
                            listaPodcasts.put(idPodcast, new Podcast(idPodcast, titulo, calidad, tipo, duracion, periocidad, nombreAutor));
                            System.out.println("Podcast de música dado de alta!");
                        }

                        if (tipo == 1) { //If video type
                            System.out.println("Indique el formato del archivo de vídeo: ");
                            String formato = teclado.nextLine();
                            senten.execute("INSERT INTO Podcast" + " (idPodcast,titulo,tipo, formato_video, duracion,periocidad,autor)" + " VALUES ('" + idPodcast + "','" + titulo + "','" + tipo + "','" + formato + "','" + duracion + "','" + periocidad + "','" + autor + "')");

                            listaPodcasts.put(idPodcast, new Podcast(idPodcast, titulo, tipo, formato, duracion, periocidad, nombreAutor));
                            System.out.println("Podcast de vídeo dado de alta!");
                        }
                        break;

                    case 2: //Add new genre

                        System.out.println("Introduzca a continuación el género que quiere dar de alta");
                        String genero = teclado.nextLine();

                        Statement senten2 = con.createStatement();//Insert SQL sentence to call genres
                        senten2.execute("SELECT * from generos");
                        senten2.execute("select max(idGeneros)+1 as proxId from generos");//Indicate last genre 
                        int idGenero = 0;
                        ResultSet resultado2 = senten2.getResultSet();
    
                        if (resultado2.next()) {
                            idGenero = Integer.parseInt(resultado2.getString("proxId"));
                        }
                        senten2.execute("INSERT INTO Generos" + "(idGeneros, nombre)" + " VALUES ('" + idGenero + "','" + genero + "')");
                        break;

                    case 3: //Update podcast genres

                        //Look for existent podcast with an instance from Map.Entry class to iterate over listePodcast map
                        System.out.println("Indique a continuación el podcast al que quiere asignar un género: ");
                        for (Map.Entry entrada : listaPodcasts.entrySet()) {
                            System.out.println(entrada.getValue()); 
}
                        int podcastEscollido = Integer.parseInt(teclado.nextLine());//Select id from podcast's map

                        //Execute SQL sentence to get values from genres to make assign
                        System.out.println("Introduzca a continuación el género que quiere asignar: ");
                        Statement senten3 = con.createStatement(); 
                        senten3.execute("SELECT * from generos");
                        ResultSet resultado3 = senten3.getResultSet();

                        while (resultado3.next()) {
                            System.out.println((resultado3.getString("idGeneros")) + ". " + resultado3.getString("nombre"));
                        }

                        int xeneroEscollido = Integer.parseInt(teclado.nextLine());

                        //If selected genre has already the podcast
                        if (listaPodcasts.get(podcastEscollido).getListaGeneros().contains(new Genero(xeneroEscollido))) {
                            System.out.println("Ese género ya está asignado al podcast!");
                        } else { //Create new ArrayList to store new assign and send it back to DB

                            ArrayList<Genero> generosActualizados = listaPodcasts.get(podcastEscollido).getListaGeneros();
                            ResultSet rsNombreXenero = senten3.executeQuery("SELECT nombre from Generos where idGeneros=" + xeneroEscollido);
                            String nome = "";

                            if (rsNombreXenero.next()) {
                                nome = rsNombreXenero.getString("nombre");
                                generosActualizados.add(new Genero(xeneroEscollido, nome));
                                listaPodcasts.get(podcastEscollido).setListaGeneros(generosActualizados);
                                senten3.executeUpdate("INSERT INTO gen_pod" + "(idPodcat, idGenero)" + " VALUES ('" + podcastEscollido + "','" + xeneroEscollido + "');");
                            }
                        }
                        break;


                    case 4: //Remove podcast
                        System.out.println("Indroduzca a continuación el podcast que quiere eliminar: ");
                        for (Map.Entry entrada2 : listaPodcasts.entrySet()) { //Iterate to get instance from Map.entry
                            System.out.println(entrada2.getValue());
                        }

                        int eliminaridPodcast = Integer.parseInt(teclado.nextLine());
                        Statement senten4 = con.createStatement();
                        senten4.executeUpdate("DELETE from gen_pod where idPodcat=" + eliminaridPodcast);
                        //Delete twice to remove foreign key that avoid delete

                        senten4.executeUpdate("delete from Podcast where idPodcast=" + eliminaridPodcast);
                        //Executed our queries to remove podcast, we'll remove it from the map
                        listaPodcasts.remove(eliminaridPodcast);
                        break;


                    case 5: //View podcast from DB
                        Statement sentencia = con.createStatement();
                        sentencia.execute("select * from podcast");

                        ResultSet resul = sentencia.getResultSet();//Accessing data with rows and columns
                        while (resul.next()) {
                            Podcast pod = new Podcast();
                            pod.setIdPodcast(resul.getInt("idPodcast"));
                            pod.setTitulo(resul.getString("titulo"));
                            pod.setTipo(resul.getInt("tipo"));
                            pod.setCalidad(resul.getString("calidad"));
                            pod.setDuracion(resul.getInt("duracion"));
                            pod.setPeriocidad(resul.getString("periocidad"));
                            pod.setNombreAutor(resul.getString("autor"));
                            pod.setFormatoVideo(resul.getString("formato_video"));
                            System.out.println(pod.toString());
                        }
                        break;


                    case 6: //View podcast and generate XML

                        //Iterate over values of listaPodcast with Map.entry and print values
                        for (Map.Entry entrada3 : listaPodcasts.entrySet()) {
                            System.out.println(entrada3.getValue());
                        }
                        System.out.println("Introduzca a continuación el podcast del que desee generar un XML");
                        int idXML = Integer.parseInt(teclado.nextLine());
                        System.out.println(listaPodcasts.get(idXML));
                        //x instance from podcast represent variable with value we pick from menu
                        Podcast x = listaPodcasts.get(idXML);

                        try {//We'll generate documentation with DocumentBuilder class
                            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                            DocumentBuilder db = dbf.newDocumentBuilder();
                            Document doc = db.newDocument();

                            //Root element
                            Element podcastXML = doc.createElement("Podcast");
                            doc.appendChild(podcastXML);

                            if (x.getTipo() == 0) {
                                Attr attr = doc.createAttribute("tipo");
                                attr.setValue("musical");
                                podcastXML.setAttributeNode(attr);

                                Element idP = doc.createElement("IdPodcast");
                                idP.appendChild(doc.createTextNode(Integer.toString(x.getIdPodcast())));
                                podcastXML.appendChild(idP);

                                Element tit = doc.createElement("titulo");
                                tit.appendChild(doc.createTextNode(x.getTitulo()));
                                podcastXML.appendChild(tit);

                                Element calidad = doc.createElement("calidad");
                                calidad.appendChild(doc.createTextNode(x.getCalidad()));
                                podcastXML.appendChild(calidad);

                                Element dura = doc.createElement("duracion");
                                dura.appendChild(doc.createTextNode(Integer.toString(x.getDuracion())));
                                podcastXML.appendChild(dura);

                                Element perio = doc.createElement("periocidad");
                                perio.appendChild(doc.createTextNode(x.getPeriocidad()));
                                podcastXML.appendChild(perio);

                                Element aut = doc.createElement("autor");
                                aut.appendChild(doc.createTextNode(x.getNombreAutor()));
                                podcastXML.appendChild(aut);

                                if (!x.getListaGeneros().isEmpty()) {
                                    Element generos = doc.createElement("generos");

                                    podcastXML.appendChild(generos);
                                    System.out.println("generos");
                                    for (int i = 0; i < x.getListaGeneros().size(); i++) {
                                        Element gener = doc.createElement("genero");
                                        gener.appendChild(doc.createTextNode(x.getListaGeneros().get(i).getNombre()));
                                        generos.appendChild(gener);
                                    }
                                }
                            }

                            if (x.getTipo() == 1) {
                                Attr attr = doc.createAttribute("tipo");
                                attr.setValue("video");

                                podcastXML.setAttributeNode(attr);
                                Element idP = doc.createElement("IdPodcast");
                                idP.appendChild(doc.createTextNode(Integer.toString(x.getIdPodcast())));

                                podcastXML.appendChild(idP);
                                Element tit = doc.createElement("titulo");
                                tit.appendChild(doc.createTextNode(x.getTitulo()));

                                podcastXML.appendChild(tit);
                                Element form = doc.createElement("formato");
                                form.appendChild(doc.createTextNode(x.getFormatoVideo()));

                                podcastXML.appendChild(form);
                                Element dura = doc.createElement("duracion");
                                dura.appendChild(doc.createTextNode(Integer.toString(x.getDuracion())));
 
                                podcastXML.appendChild(dura);
                                Element perio = doc.createElement("periocidad");
                                perio.appendChild(doc.createTextNode(x.getPeriocidad()));

                                podcastXML.appendChild(perio);
                                Element aut = doc.createElement("autor");
                                aut.appendChild(doc.createTextNode(x.getNombreAutor()));

                                podcastXML.appendChild(aut);
                                if (!x.getListaGeneros().isEmpty()) {
                                    Element generos = doc.createElement("generos");

                                    podcastXML.appendChild(generos);
                                    System.out.println("generos");
                                    for (int i = 0; i < x.getListaGeneros().size(); i++) {
                                        Element gener = doc.createElement("genero");
                                        gener.appendChild(doc.createTextNode(x.getListaGeneros().get(i).getNombre()));
                                        generos.appendChild(gener);
                                    }
                                }
                            }

                            //Create isntance of Transformer class
                            Transformer transformer = TransformerFactory.newInstance().newTransformer();
                            DOMSource source = new DOMSource(doc);
                            //Create XML file
                            StreamResult result = new StreamResult(new File("Podcast.xml"));
                            transformer.transform(source, result);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                        break;


                    case 7:
                        System.out.println("Fin do programa!");
                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    //Method to store on ArrayList genres and podcast to avoid more consults to DB
    public static void lanzarPodcast(Connection con) throws SQLException {

        PreparedStatement sta1 = con.prepareStatement("SELECT * from Podcast");
        PreparedStatement sta2 = con.prepareStatement("SELECT * from Podcast");
        PreparedStatement sta3 = con.prepareStatement("SELECT * from Podcast");
        ResultSet res = sta1.executeQuery("SELECT * FROM Podcast");
        String autor = "";

        //While we query SELECT * FROM Podcast and receive data
        while (res.next()) {//New sentence, ask for name, surname from autor which id matches with podcast
            sta2 = con.prepareStatement("SELECT nombre, apellidos FROM Autor join Podcast on(Podcast.autor=Autor.idAutor);");

            ResultSet rs2 = sta2.executeQuery("SELECT nombre,apellidos FROM Autor join Podcast on(Autor.idAutor=" + res.getString("autor") + ")");

            if (rs2.next()) {//If we're still receiving data
                autor = (rs2.getString("nombre") + " " + rs2.getString("apellidos"));
            }

            if (Integer.parseInt(res.getString("tipo")) == 0) {
                //Placing elements according to it's constructor
                listaPodcasts.put(Integer.parseInt(res.getString("idPodcast")), new Podcast(Integer.parseInt(res.getString("idPodcast")), res.getString("titulo"), res.getString("calidad"), Integer.parseInt(res.getString("tipo")), Integer.parseInt(res.getString("duracion")), res.getString("periocidad"), autor));
            }

            if (Integer.parseInt(res.getString("tipo")) == 1) {
                listaPodcasts.put(Integer.parseInt(res.getString("idPodcast")), new Podcast(Integer.parseInt(res.getString("idPodcast")), res.getString("titulo"), Integer.parseInt(res.getString("tipo")), res.getString("formato_video"), Integer.parseInt(res.getString("duracion")), res.getString("periocidad"), autor));
            }
        }

        res = sta1.executeQuery("SELECT * FROM gen_pod");
        while (res.next()) {
            for (Map.Entry entry : listaPodcasts.entrySet()) {
                String nombre = "";
                ResultSet rs3 = sta3.executeQuery("SELECT nombre from Generos where idGeneros=" + res.getString("IdGenero"));
                if (rs3.next()) {
                    nombre = rs3.getString("nombre");
                }

                Podcast podcastX = (Podcast) (entry.getValue());//Create instance from Podcast class called podcastX
                ArrayList<Genero> xenerosPodcast = new ArrayList<Genero>();
                xenerosPodcast.clear();
                xenerosPodcast = podcastX.getListaGeneros();//Call this list from getter of ListeGeneros

                if (podcastX.getIdPodcast() == Integer.parseInt(res.getString("IdPodcat"))) {
                    xenerosPodcast.add(new Genero(Integer.parseInt(res.getString("IdGenero")), nombre));
                }
                podcastX.setListaGeneros(xenerosPodcast);
            }
        }
    }
}
