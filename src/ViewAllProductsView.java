import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViewAllProductsView extends JFrame {
    private JTable productTable;
    private JButton btnClose = new JButton("Close");

    public ViewAllProductsView(Object[][] productData) {
        // Set up frame
        setTitle("All Products");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Column names for the table
        String[] columnNames = {"Product ID", "Product Name", "Category", "Unit Price", "Supplier ID"};

        // Initialize JTable with product data and column names
        DefaultTableModel tableModel = new DefaultTableModel(productData, columnNames);
        productTable = new JTable(tableModel);
        productTable.setEnabled(false);  // Make table read-only

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(productTable);
        add(scrollPane, BorderLayout.CENTER);

        // Close button panel
        JPanel btnPanel = new JPanel();
        btnPanel.add(btnClose);
        add(btnPanel, BorderLayout.SOUTH);

        // Close button action
        btnClose.addActionListener(e -> {
            dispose(); // Close the current window
            App.getInstance().getMainScreen().setVisible(true); // Show the MainScreen again
        });
    }
    public ViewAllProductsView() {
        this(getProductDataFromDatabase()); // Call the existing constructor with fetched data
    }

    // Method to fetch product data from the database
    public static Object[][] getProductDataFromDatabase() {
        String dbURL = "jdbc:sqlite:D:/Fulbright/Year 3/OOP/Project 1/Database/StoreData";
        List<Object[]> productList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(dbURL)) {
            String query = "SELECT ProductID, ProductName, Category, UnitPrice, SupplierID FROM Product";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Iterate through the result set and add each row to the product list
            while (resultSet.next()) {
                Object[] product = {
                    resultSet.getInt("ProductID"),
                    resultSet.getString("ProductName"),
                    resultSet.getString("Category"),
                    resultSet.getDouble("UnitPrice"),
                    resultSet.getInt("SupplierID")
                };
                productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching product data from database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        // Convert list to 2D array
        return productList.toArray(new Object[0][]);
    }
    public static void main(String[] args) {
        // Retrieve product data from database
        Object[][] productData = getProductDataFromDatabase();

        // Run the view with the fetched data
        SwingUtilities.invokeLater(() -> {
            new ViewAllProductsView(productData).setVisible(true);
        });
    }
}
