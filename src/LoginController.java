import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
    private LoginScreen loginScreen;
    private static final String VALID_USERNAME = "admin1";
    private static final String VALID_PASSWORD = "admin123";


    public LoginController(LoginScreen loginScreen) {
        this.loginScreen = loginScreen;
        this.loginScreen.getBtnLogin().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginScreen.getBtnLogin()) {
            String username = loginScreen.getTxtUserName().getText().trim();
            char[] passwordChars = loginScreen.getTxtPassword().getPassword();
            String password = new String(passwordChars).trim();
            java.util.Arrays.fill(passwordChars, ' '); // Clear the password from memory after use
            
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                    "Please enter both username and password",
                    "Login Error",
                    JOptionPane.ERROR_MESSAGE);
            }
            else if (username.equals(VALID_USERNAME) && password.equals(VALID_PASSWORD)){
                System.out.println("Login with username = " + username + " and password = " + password);
                this.loginScreen.setVisible(false);
                App.getInstance().getMainScreen().setVisible(true);
            }
            else {
                JOptionPane.showMessageDialog(null,
                "Invalid username or password",
                "Login Error",
                JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
