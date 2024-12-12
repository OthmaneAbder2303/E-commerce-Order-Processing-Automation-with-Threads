package ma.ensa.ordcusthreads;

import com.google.gson.Gson;
import java.io.*;
import java.nio.file.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FileHandler {
    private final String sourceDirectory = "Input/";
    private final String successDirectory = "Output/";
    private final String failureDirectory = "Error/";

    public void handleSourceDirectory() {
        File sourceFolder = new File(sourceDirectory);
        File[] files = sourceFolder.listFiles();
        if (files != null) {
            for (File file : files) {
                try {
                    String fileContent = new String(Files.readAllBytes(file.toPath()));
                    InventoryOrder order = fromJson(fileContent);  // Use Gson to convert JSON to InventoryOrder
                    boolean customerExists = checkCustomerExistence(order.getCustomerId());
                    if (customerExists && addOrderToDatabase(order)) {
                        relocateFile(file, successDirectory);
                    } else {
                        relocateFile(file, failureDirectory);
                    }
                } catch (Exception e) {
                    relocateFile(file, failureDirectory);
                }
            }
        }
    }

    // Use Gson to parse JSON and convert to InventoryOrder
    private InventoryOrder fromJson(String json) {
        return InventoryOrder.fromJson(json);
    }

    private Boolean checkCustomerExistence(int customerId) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection conn = dbConnection.getConnection();
        String query = "SELECT * FROM customers WHERE id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Database query failed", e);
        }
    }

    private boolean addOrderToDatabase(InventoryOrder order) {
        String query = "INSERT INTO orders (id, customer_id, product_details) VALUES (?, ?, ?)";
        DatabaseConnection dbConnection = new DatabaseConnection();
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, order.getId());
            stmt.setInt(2, order.getCustomerId());
            stmt.setString(3, order.getProductDetails());
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void relocateFile(File file, String destinationDir) {
        try {
            Files.move(file.toPath(), Paths.get(destinationDir, file.getName()), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Moved file: " + file.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
