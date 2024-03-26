package poligranproyecto;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class GenerateInfoFiles {
    public static void main(String[] args) {
        try {
            // Generar archivos de prueba
            createSalesMenFile(10); // Generar información para 10 vendedores
            createProductsFile(20); // Generar información para 20 productos
            createSalesManInfoFile(10); // Generar información de 10 vendedores
            System.out.println("Archivos de prueba generados exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al generar los archivos: " + e.getMessage());
        }
    }

    private static void createSalesMenFile(int salesmanCount) throws IOException {
        Random random = new Random();
        try (PrintWriter salesMenFile = new PrintWriter(new FileWriter("salesmen_info.txt"))) {
            for (int i = 0; i < salesmanCount; i++) {
                String typeDocument = "Tipo" + (random.nextInt(5) + 1); // Tipo de documento aleatorio
                String documentNumber = String.valueOf(random.nextInt(1000000)); // Número de documento aleatorio
                salesMenFile.println(typeDocument + ";" + documentNumber);
            }
        }
    }

    private static void createProductsFile(int productsCount) throws IOException {
        Random random = new Random();
        try (PrintWriter productsFile = new PrintWriter(new FileWriter("products_info.txt"))) {
            for (int i = 0; i < productsCount; i++) {
                String productId = "IDProducto" + (i + 1);
                String productName = "Producto" + (i + 1);
                double price = 10 + random.nextDouble() * 90; // Precio aleatorio entre 10 y 100
                productsFile.println(productId + ";" + productName + ";" + price);
            }
        }
    }

    private static void createSalesManInfoFile(int salesmanCount) throws IOException {
        Random random = new Random();
        try (PrintWriter salesmanInfoFile = new PrintWriter(new FileWriter("salesman_info.txt"))) {
            for (int i = 0; i < salesmanCount; i++) {
                String typeDocument = "Tipo" + (random.nextInt(5) + 1); // Tipo de documento aleatorio
                String documentNumber = String.valueOf(random.nextInt(1000000)); // Número de documento aleatorio
                String firstName = "Nombre" + (i + 1);
                String lastName = "Apellido" + (i + 1);
                salesmanInfoFile.println(typeDocument + ";" + documentNumber + ";" + firstName + ";" + lastName);
            }
        }
    }
}
