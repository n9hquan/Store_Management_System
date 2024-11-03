import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataLayer {
    private Connection connection;

    public DataLayer() {
        try {
            // Load SQLite driver
            Class.forName("org.sqlite.JDBC");
            
            // Connect to your database file
            String dbPath = "D:\\Fulbright\\Year 3\\OOP\\Project 1\\Database\\StoreData";
            this.connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        } catch (Exception e) {
            System.out.println("Database connection error!");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public Product loadProductID(int id) {
        try {
            // Using prepared statement instead of string concatenation
            PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM Product WHERE ProductID = ?"
            );
            statement.setInt(1, id);
            
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Product product = new Product();
                product.setProductID(resultSet.getInt("ProductID"));
                product.setName(resultSet.getString("ProductName"));
                product.setPrice(resultSet.getDouble("UnitPrice"));
                product.setCategory(resultSet.getString("Category"));
                product.setSupplierID(resultSet.getInt("SupplierID"));
                
                resultSet.close();
                statement.close();
                return product;
            }
        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
        }
        return null;
    }

    public boolean registerCustomer(Customer customer) {
        try {
            // Prepare the SQL statement for inserting a new customer
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO Customer (CustomerName, PhoneNumber, Email) VALUES (?, ?, ?)"
            );
            statement.setString(1, customer.getCustomerName());
            statement.setString(2, customer.getPhoneNumber()); // This can be null
            statement.setString(3, customer.getEmail()); // This can be null
            
            // Execute the insert operation
            statement.executeUpdate();
            statement.close();
            return true;
            
        } catch (SQLException e) {
            System.out.println("Database access error while registering customer!");
            e.printStackTrace();
            return false;
        }
    }

    public List<Product> searchProductsByCriteria(Product product) {
        List<Product> matchingProducts = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM Product WHERE 1=1");
        List<Object> parameters = new ArrayList<>();
    
        // Build the query based on filled fields
        if (product.getProductID() > 0) {
            query.append(" AND ProductID = ?");
            parameters.add(product.getProductID());
        }
        if (product.getName() != null && !product.getName().isEmpty()) {
            query.append(" AND ProductName LIKE ?");
            parameters.add("%" + product.getName() + "%");
        }
        if (product.getCategory() != null && !product.getCategory().isEmpty()) {
            query.append(" AND Category LIKE ?");
            parameters.add("%" + product.getCategory() + "%");
        }
        if (product.getPrice() > 0) {
            query.append(" AND UnitPrice = ?");
            parameters.add(product.getPrice());
        }
        if (product.getSupplierID() > 0) {
            query.append(" AND SupplierID = ?");
            parameters.add(product.getSupplierID());
        }
    
        try {
            PreparedStatement statement = connection.prepareStatement(query.toString());
            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }
    
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product p = new Product();
                p.setProductID(resultSet.getInt("ProductID"));
                p.setName(resultSet.getString("ProductName"));
                p.setPrice(resultSet.getDouble("UnitPrice"));
                p.setCategory(resultSet.getString("Category"));
                p.setSupplierID(resultSet.getInt("SupplierID"));
                matchingProducts.add(p);
            }
            
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
        }
    
        return matchingProducts;
    }
    
    public List<Customer> searchCustomersByCriteria(Customer customer) {
    List<Customer> matchingCustomers = new ArrayList<>();
    StringBuilder query = new StringBuilder("SELECT * FROM Customer WHERE 1=1");
    List<Object> parameters = new ArrayList<>();

    // Build the query based on filled fields
    if (customer.getCustomerID() > 0) {
        query.append(" AND CustomerID = ?");
        parameters.add(customer.getCustomerID());
    }
    if (customer.getCustomerName() != null && !customer.getCustomerName().isEmpty()) {
        query.append(" AND CustomerName LIKE ?");
        parameters.add("%" + customer.getCustomerName() + "%");
    }
    if (customer.getPhoneNumber() != null && !customer.getPhoneNumber().isEmpty()) {
        query.append(" AND PhoneNumber LIKE ?");
        parameters.add("%" + customer.getPhoneNumber() + "%");
    }
    if (customer.getEmail() != null && !customer.getEmail().isEmpty()) {
        query.append(" AND Email LIKE ?");
        parameters.add("%" + customer.getEmail() + "%");
    }

    try {
        PreparedStatement statement = connection.prepareStatement(query.toString());
        for (int i = 0; i < parameters.size(); i++) {
            statement.setObject(i + 1, parameters.get(i));
        }

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Customer c = new Customer();
            c.setCustomerID(resultSet.getInt("CustomerID"));
            c.setCustomerName(resultSet.getString("CustomerName"));
            c.setPhoneNumber(resultSet.getString("PhoneNumber"));
            c.setEmail(resultSet.getString("Email"));
            matchingCustomers.add(c);
        }
        
        resultSet.close();
        statement.close();
        } catch (SQLException e) {
            System.out.println("Database access error!");
            e.printStackTrace();
        }

        return matchingCustomers;
    }

    // Method to check if a customer exists by exact name (case-sensitive)
    public boolean customerExistsByName(String customerName) {
        String sql = "SELECT COUNT(*) FROM Customer WHERE CustomerName = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, customerName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Returns true if count is greater than 0
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception appropriately
        }
        return false; // Default return false if there's an exception or no result
    }

    // Method to check if a product exists by name (case-insensitive)
    public boolean productExistsByName(String productName) {
        String sql = "SELECT COUNT(*) FROM Product WHERE LOWER(ProductName) = LOWER(?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, productName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Returns true if count is greater than 0
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exception appropriately
        }
        return false; // Default return false if there's an exception or no result
    }

    public double getProductUnitPrice(String productName) {
        double unitPrice = 0.0;

        String query = "SELECT UnitPrice FROM Product WHERE ProductName = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, productName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                unitPrice = resultSet.getDouble("UnitPrice");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception (e.g., log it or show a message)
        }
        
        return unitPrice;
    }

    public int getCustomerIdByName(String customerName) {
        String query = "SELECT CustomerID FROM Customer WHERE CustomerName = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, customerName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("CustomerID");
            }
        } catch (SQLException e) {
            System.out.println("Error getting customer ID!");
            e.printStackTrace();
        }
        return -1; // Return -1 to indicate error or customer not found
    }

    public int getProductIdByName(String productName) {
        String query = "SELECT ProductID FROM Product WHERE ProductName = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, productName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("ProductID");
            }
        } catch (SQLException e) {
            System.out.println("Error getting product ID!");
            e.printStackTrace();
        }
        return -1; // Return -1 to indicate error or product not found
    }

    public int insertCustomerOrder(int customerId, String orderDate, double totalOrderPrice) {
        String query = "INSERT INTO CustomerOrder (CustomerID, OrderDate, TotalOrderPrice) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, customerId);
            stmt.setString(2, orderDate);
            stmt.setDouble(3, totalOrderPrice);
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("Creating order failed, no rows affected.");
                return -1;
            }

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                System.out.println("Creating order failed, no ID obtained.");
                return -1;
            }
        } catch (SQLException e) {
            System.out.println("Database error while inserting customer order!");
            e.printStackTrace();
            return -1;
        }
    }

    public boolean insertItemOrder(int orderId, int productId, int quantity, double unitPrice, double totalPrice) {
        String query = "INSERT INTO ItemOrder (OrderID, ProductID, Quantity, UnitPrice, TotalPrice) " +
                      "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            stmt.setInt(2, productId);
            stmt.setInt(3, quantity);
            stmt.setDouble(4, unitPrice);
            stmt.setDouble(5, totalPrice);
            
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
            
        } catch (SQLException e) {
            System.out.println("Database error while inserting item order!");
            e.printStackTrace();
            return false;
        }
    }
}