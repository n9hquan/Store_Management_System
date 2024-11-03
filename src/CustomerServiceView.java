import javax.swing.*;
import java.awt.*;

public class CustomerServiceView extends JFrame {
    private JButton btnRegister = new JButton("Register New Customer");
    private JButton btnCheckInfo = new JButton("Check Customer Information");
    private JButton btnReturn = new JButton("Return to Main Menu");

    public JButton getBtnRegister() {
        return btnRegister;
    }

    public JButton getBtnCheckInfo() {
        return btnCheckInfo;
    }

    public JButton getBtnReturn() {
        return btnReturn;
    }

    public CustomerServiceView() {
        // Basic frame setup
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Only close this window, not entire application
        this.setSize(400, 300);
        setLocationRelativeTo(null);

        // Set button sizes
        btnRegister.setPreferredSize(new Dimension(200, 50));
        btnCheckInfo.setPreferredSize(new Dimension(200, 50));
        btnReturn.setPreferredSize(new Dimension(200, 50));

        // Create and setup title panel
        JLabel title = new JLabel("Customer Service");
        title.setFont(new Font("Sans Serif", Font.BOLD, 20));
        JPanel panelTitle = new JPanel();
        panelTitle.add(title);

        // Create panels for buttons
        JPanel panelRegister = new JPanel();
        panelRegister.add(btnRegister);

        JPanel panelCheckInfo = new JPanel();
        panelCheckInfo.add(btnCheckInfo);

        JPanel panelReturn = new JPanel();
        panelReturn.add(btnReturn);

        // Add panels to frame
        this.getContentPane().add(panelTitle);
        this.getContentPane().add(panelRegister);
        this.getContentPane().add(panelCheckInfo);
        this.getContentPane().add(panelReturn);
    }

}