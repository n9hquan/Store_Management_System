import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterCustomerController implements ActionListener {
    private RegisterCustomerView registerView;
    private DataLayer dataLayer;

    public RegisterCustomerController(RegisterCustomerView registerView) {
        this.registerView = registerView;
        this.dataLayer = new DataLayer();
        this.registerView.getSubmitButton().addActionListener(this);
        this.registerView.getCancelButton().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerView.getSubmitButton()) {
            handleSubmit();
        } else if (e.getSource() == registerView.getCancelButton()) {
            registerView.dispose(); // Close the registration view
            App.getInstance().getMainScreen().setVisible(true); // Return to the main menu
        }
    }

    private void handleSubmit() {
        String name = registerView.getNameField().getText().trim();
        String phone = registerView.getPhoneField().getText().trim();
        String email = registerView.getEmailField().getText().trim();

        // Validate the name field
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(registerView, "Name is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create a new Customer object
        Customer customer = new Customer();
        customer.setCustomerName(name);
        customer.setPhoneNumber(phone.isEmpty() ? null : phone);
        customer.setEmail(email.isEmpty() ? null : email);

        // Register the customer in the database
        boolean success = dataLayer.registerCustomer(customer);

        // Provide user feedback
        if (success) {
            JOptionPane.showMessageDialog(registerView, "Customer registered successfully!");
            registerView.dispose(); // Close the view after successful registration
        } else {
            JOptionPane.showMessageDialog(registerView, "Failed to register customer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
