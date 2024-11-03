import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JFrame {
    private JTextField txtUserName = new JTextField(10);
    private JPasswordField txtPassword = new JPasswordField(10);
    private JButton    btnLogin    = new JButton("Login");
    
    public JButton getBtnLogin() {
        return btnLogin;
    }
    
    public JPasswordField getTxtPassword() {  
        return txtPassword;
    }
    
    public JTextField getTxtUserName() {
        return txtUserName;
    }
    
    public LoginScreen() {
        this.setSize(300, 150);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        
        // Add header label
        JLabel headerLabel = new JLabel("Store Management System");
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(headerLabel);
        
        // Create main panel with GridLayout
        JPanel main = new JPanel(new GridLayout(2, 2, 6, 6));
        main.add(new JLabel("Username:"));
        main.add(txtUserName);
        main.add(new JLabel("Password:"));
        main.add(txtPassword);
        
        // Add some padding around the main panel
        JPanel paddedMain = new JPanel();
        paddedMain.setLayout(new BoxLayout(paddedMain, BoxLayout.X_AXIS));
        paddedMain.add(Box.createHorizontalStrut(6));
        paddedMain.add(main);
        paddedMain.add(Box.createHorizontalStrut(6));
        
        this.getContentPane().add(paddedMain);
        
        // Add login button
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.getContentPane().add(btnLogin);
        
        // Center the window on screen
        setLocationRelativeTo(null);
    }
}