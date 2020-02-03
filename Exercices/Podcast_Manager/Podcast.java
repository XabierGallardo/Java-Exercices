package tarefa12.ii;

import java.util.ArrayList;

//Start with Podcast class, create attributes, getters & setters
public class Podcast {

    public int idPodcast;
    public String titulo;
    public String calidad;
    public int tipo;
    public String formato;
    public int duracion;
    public String periocidad;
    public String nombreAutor;
    
    //List to associate Podcast with Genres
    ArrayList<Genero> listaGeneros = new ArrayList<Genero>();

    //Empty constructor
    public Podcast() {
    }
    
    //Constructor without formato attribute
    public Podcast(int idPodcast, String titulo, String calidad, int tipo, int duracion, String periocidad, String autor) {
        this.idPodcast = idPodcast;
        this.titulo = titulo;
        this.calidad = calidad;
        this.tipo = tipo;
        this.duracion = duracion;
        this.periocidad = periocidad;
        this.nombreAutor = autor;
    }
    
    //Construtor without calidad attribute
    public Podcast(int idPodcast, String titulo, int tipo, String formato, int duracion, String periocidad, String autor) {
        this.idPodcast = idPodcast;
        this.titulo = titulo;
        this.tipo = tipo;
        this.formato = formato;
        this.duracion = duracion;
        this.periocidad = periocidad;
        this.nombreAutor = autor;
    }

    @Override
    public String toString() {
        return +idPodcast + ". " + titulo + " do autor " + nombreAutor;

    }

    public int getIdPodcast() {
        return idPodcast;
    }

    public void setIdPodcast(int idPodcast) {
        this.idPodcast = idPodcast;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getCalidad() {
        return calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getPeriocidad() {
        return periocidad;
    }

    public void setPeriocidad(String periocidad) {
        this.periocidad = periocidad;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getFormatoVideo() {
        return formato;
    }

    public void setFormatoVideo(String formatoVideo) {
        this.formato = formatoVideo;
    }

    public ArrayList<Genero> getListaGeneros() {
        return listaGeneros;
    }

    public void setListaGeneros(ArrayList<Genero> listaGeneros) {
        this.listaGeneros = listaGeneros;
    }

    public String mostrarPodcasts() {
        if (tipo == 0) {
            return "O audio podcast ten un id " + idPodcast + " de nome " + titulo + " do autor " + nombreAutor + ". Publícase cunha periocidade " + periocidad + " e conta cunha calidade de " + calidad + this.mostrarGeneros();
        } else {
            return "O audio podcast ten un id " + idPodcast + " de nome " + titulo + " do autor " + nombreAutor + ". Publícase cunha periocidade " + periocidad + " e conta cun formato de " + formato + this.mostrarGeneros();
        }
    }

    public String mostrarGeneros() {
        if (!listaGeneros.isEmpty()) {
            String gen = "";
            for (int i = 0; i < listaGeneros.size(); i++) {
                gen += listaGeneros.get(i).getNombre() + ' ';
            }
            return " conta cos seguintes xéneros " + gen;
        } else {
            return " ";
        }
    }

}
