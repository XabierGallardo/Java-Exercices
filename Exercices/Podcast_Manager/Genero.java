package tarefa12.ii;

public class Genero {
    
    public int id;
    public String nombre;

    public Genero(int idGenero, String nombre) {
        this.id = idGenero;
        this.nombre = nombre;
    }

    public Genero(int idGenero) {
        this.id = idGenero;
    }

    public int getIdGenero() {
        return id;
    }

    public void setIdGenero(int idGenero) {
        this.id = idGenero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    public boolean equals (Object obj){
        if (this==obj){
            return true;
        }
        if (obj ==null){
            return false;
        }
        if(getClass() !=obj.getClass()){
            return false;
        }
        final Genero other = (Genero) obj;
        if (this.id !=other.id){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
}
