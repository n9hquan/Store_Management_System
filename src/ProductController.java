import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductController {
    private DataLayer dataLayer; // Reference to the data layer
    private ProductView productView; // Reference to your product view

    public ProductController(ProductView productView) {
        this.productView = productView;
        this.dataLayer = new DataLayer(); // Initialize the data layer

        // Set up action listeners
        productView.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSearch();
            }
        });

        productView.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productView.dispose(); // Close the dialog
                App.getInstance().getMainScreen().setVisible(true);
            }
        });
    }

    public void handleSearch() {
        // Validate input
        if (isAnyFieldFilled()) {
            // Collect search parameters
            Product product = new Product();
            if (!productView.getTxtProductID().getText().trim().isEmpty()) {
                product.setProductID(Integer.parseInt(productView.getTxtProductID().getText().trim()));
            }
            if (!productView.getTxtProductName().getText().trim().isEmpty()) {
                product.setName(productView.getTxtProductName().getText().trim());
            }
            if (!productView.getTxtCategory().getText().trim().isEmpty()) {
                product.setCategory(productView.getTxtCategory().getText().trim());
            }
            if (!productView.getTxtUnitPrice().getText().trim().isEmpty()) {
                product.setPrice(Double.parseDouble(productView.getTxtUnitPrice().getText().trim()));
            }
            if (!productView.getTxtSupplierID().getText().trim().isEmpty()) {
                product.setSupplierID(Integer.parseInt(productView.getTxtSupplierID().getText().trim()));
            }

            // Perform the search with the populated product object
            List<Product> results = dataLayer.searchProductsByCriteria(product);
            if (results.isEmpty()) {
                JOptionPane.showMessageDialog(productView, "No products found matching the criteria.", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Display results in the view
                productView.displaySearchResults(results);
            }
        } else {
            JOptionPane.showMessageDialog(productView, "Please enter at least one search criterion.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isAnyFieldFilled() {
        return !productView.getTxtProductID().getText().trim().isEmpty() ||
               !productView.getTxtProductName().getText().trim().isEmpty() ||
               !productView.getTxtCategory().getText().trim().isEmpty() ||
               !productView.getTxtUnitPrice().getText().trim().isEmpty() ||
               !productView.getTxtSupplierID().getText().trim().isEmpty();
    }
}
