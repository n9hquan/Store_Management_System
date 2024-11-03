import javax.swing.*;
import java.awt.*;

public class RegisterCustomerView extends JFrame {
    private JTextField nameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JButton submitButton;
    private JButton cancelButton;

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getPhoneField() {
        return phoneField;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public RegisterCustomerView() {
        setTitle("Register Customer");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen

        // Create panel with a grid layout for form fields and buttons
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        
        // Name label and text field
        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);
        
        // Phone label and text field
        panel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        panel.add(phoneField);
        
        // Email label and text field
        panel.add(new JLabel("Email:"));
        emailField = new JTextField();
        panel.add(emailField);
        
        // Submit button
        submitButton = new JButton("Submit");
        panel.add(submitButton);

        // Clear button
        cancelButton = new JButton("Cancel");
        panel.add(cancelButton);

        // Add panel to the frame
        add(panel);

    }
    
}
