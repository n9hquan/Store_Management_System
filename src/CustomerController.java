import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CustomerController {
    private DataLayer dataLayer; // Reference to the data layer
    private CustomerView customerView; // Reference to your customer view

    public CustomerController(CustomerView customerView) {
        this.customerView = customerView;
        this.dataLayer = new DataLayer(); // Initialize the data layer

        // Set up action listeners
        customerView.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSearch();
            }
        });

        customerView.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerView.dispose(); // Close the dialog
                App.getInstance().getMainScreen().setVisible(true);
            }
        });
    }

    public void handleSearch() {
        // Validate input
        if (isAnyFieldFilled()) {
            // Collect search parameters
            Customer customer = new Customer();
            if (!customerView.getTxtCustomerID().getText().trim().isEmpty()) {
                customer.setCustomerID(Integer.parseInt(customerView.getTxtCustomerID().getText().trim()));
            }
            if (!customerView.getTxtCustomerName().getText().trim().isEmpty()) {
                customer.setCustomerName(customerView.getTxtCustomerName().getText().trim());
            }
            if (!customerView.getTxtPhoneNumber().getText().trim().isEmpty()) {
                customer.setPhoneNumber(customerView.getTxtPhoneNumber().getText().trim());
            }
            if (!customerView.getTxtEmail().getText().trim().isEmpty()) {
                customer.setEmail(customerView.getTxtEmail().getText().trim());
            }

            // Perform the search with the populated customer object
            List<Customer> results = dataLayer.searchCustomersByCriteria(customer);
            if (results.isEmpty()) {
                JOptionPane.showMessageDialog(customerView, "No customers found matching the criteria.", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Display results in the view
                customerView.displaySearchResults(results);
            }
        } else {
            JOptionPane.showMessageDialog(customerView, "Please enter at least one search criterion.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isAnyFieldFilled() {
        return !customerView.getTxtCustomerID().getText().trim().isEmpty() ||
               !customerView.getTxtCustomerName().getText().trim().isEmpty() ||
               !customerView.getTxtPhoneNumber().getText().trim().isEmpty() ||
               !customerView.getTxtEmail().getText().trim().isEmpty();
    }
}
