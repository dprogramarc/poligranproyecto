package poligranproyecto;

class Venta {
    private String idVendedor;
    private String nombreProducto;
    private int cantidad;

    public Venta(String idVendedor, String nombreProducto, int cantidad) {
        this.idVendedor = idVendedor;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
    }

    public String getIdVendedor() {
        return idVendedor;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }
}