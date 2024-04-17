package poligranproyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import poligranproyecto.Vendedor;
import poligranproyecto.Producto;
import poligranproyecto.Venta;

public class Main {
    public static void main(String[] args) {
        try {
            // Leer información de vendedores y ventas
            List<Vendedor> vendedores = leerInfoVendedores("vendedores.txt");
            List<Venta> ventas = leerInfoVentas("ventas.txt", "productos.txt");

            // Generar archivo de reporte de ventas de los vendedores
            generarReporteVendedores(vendedores, ventas);

            // Generar archivo de información de productos vendidos
            generarReporteProductos(ventas);

            System.out.println("Archivos de reporte generados exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al generar los archivos de reporte: " + e.getMessage());
        }
    }

    private static List<Vendedor> leerInfoVendedores(String fileName) throws IOException {
        List<Vendedor> vendedores = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                String nombre = parts[0].trim();
                String id = parts[1].trim();
                vendedores.add(new Vendedor(nombre, id));
            } else {
                System.out.println("Línea inválida en el archivo: " + line);
            }
        }
        reader.close();
        return vendedores;
    }

    private static List<Venta> leerInfoVentas(String salesFileName, String productsFileName) throws IOException {
        List<Venta> ventas = new ArrayList<>();
        Map<String, Producto> productos = new HashMap<>();

        // Leer información de productos
        BufferedReader productReader = new BufferedReader(new FileReader(productsFileName));
        String line;
        while ((line = productReader.readLine()) != null) {
            String[] parts = line.split(",");
            String nombre = parts[0].trim();
            double precio = Double.parseDouble(parts[1].trim());
            productos.put(nombre, new Producto(nombre, precio));
        }
        productReader.close();

        // Leer información de ventas
        BufferedReader salesReader = new BufferedReader(new FileReader(salesFileName));
        while ((line = salesReader.readLine()) != null) {
            String[] parts = line.split(";");
            if (parts.length >= 4) {
                String idVendedor = parts[0].trim();
                String nombreProducto = parts[1].trim() + " " + parts[2].trim();
                int cantidad = Integer.parseInt(parts[3].trim());
                ventas.add(new Venta(idVendedor, nombreProducto, cantidad));
            } else {
                System.out.println("Línea inválida en el archivo de ventas: " + line);
            }
        }
        salesReader.close();

        return ventas;
    }

    private static void generarReporteVendedores(List<Vendedor> vendedores, List<Venta> ventas) throws IOException {
        FileWriter writer = new FileWriter("reporte_vendedores.txt");
        for (Vendedor vendedor : vendedores) {
            double totalVentas = 0;
            for (Venta venta : ventas) {
                if (venta.getIdVendedor().equals(vendedor.getId())) {
                    Producto producto = buscarProducto(venta.getNombreProducto(), ventas);
                    double ventaTotal = producto.getPrecio() * venta.getCantidad();
                    totalVentas += ventaTotal;
                }
            }
            writer.write(vendedor.getNombre() + "," + vendedor.getId() + "," + totalVentas + "\n");
        }
        writer.close();
    }

    private static void generarReporteProductos(List<Venta> ventas) throws IOException {
        FileWriter writer = new FileWriter("reporte_productos.txt");
        Map<String, Integer> productosVendidos = new HashMap<>();
        for (Venta venta : ventas) {
            String nombreProducto = venta.getNombreProducto();
            int cantidad = venta.getCantidad();
            productosVendidos.put(nombreProducto, productosVendidos.getOrDefault(nombreProducto, 0) + cantidad);
        }
        for (Map.Entry<String, Integer> entry : productosVendidos.entrySet()) {
            String nombreProducto = entry.getKey();
            int cantidad = entry.getValue();
            Producto producto = buscarProducto(nombreProducto, ventas);
            double totalVentas = producto.getPrecio() * cantidad;
            writer.write(nombreProducto + "," + cantidad + "," + totalVentas + "\n");
        }
        writer.close();
    }

    private static Producto buscarProducto(String nombreProducto, List<Venta> ventas) {
        for (Venta venta : ventas) {
            if (venta.getNombreProducto().equals(nombreProducto)) {
                return new Producto(nombreProducto, venta.getCantidad());
            }
        }
        return null;
    }
}