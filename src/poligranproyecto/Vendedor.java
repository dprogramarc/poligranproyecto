package poligranproyecto;

class Vendedor {
    private String nombre;
    private String id;

    public Vendedor(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
        
    }
    public String getId() {
        return id;
    }
}