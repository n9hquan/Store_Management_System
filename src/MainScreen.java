import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {
    private JButton btnMakeOrder = new JButton("Make Order");
    private JButton btnCheckProduct = new JButton("Check Product");
    private JButton btnCustomerService = new JButton("Customer Service");
    private JButton btnOrderRecords = new JButton("Order Records");

    public JButton getBtnMakeOrder() {
        return btnMakeOrder;
    }

    public JButton getBtnCheckProduct() {
        return btnCheckProduct;
    }

    public JButton getBtnCustomerService() {
        return btnCustomerService;
    }

    public JButton getBtnOrderRecords() {
        return btnOrderRecords;
    }

    public MainScreen() {
        // Basic frame setup
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 400);
        setLocationRelativeTo(null);

        // Set button sizes
        btnMakeOrder.setPreferredSize(new Dimension(150, 50));
        btnCheckProduct.setPreferredSize(new Dimension(150, 50));
        btnCustomerService.setPreferredSize(new Dimension(150, 50));
        btnOrderRecords.setPreferredSize(new Dimension(150, 50));

        // Create and setup title panel
        JLabel title = new JLabel("Store Management System");
        title.setFont(new Font("Sans Serif", Font.BOLD, 24));
        JPanel panelTitle = new JPanel();
        panelTitle.add(title);

        // Create button panels
        JPanel panelButtonsTop = new JPanel();
        panelButtonsTop.add(btnMakeOrder);
        panelButtonsTop.add(btnCheckProduct);

        JPanel panelButtonsBottom = new JPanel();
        panelButtonsBottom.add(btnCustomerService);
        panelButtonsBottom.add(btnOrderRecords);

        // Add panels to frame
        this.getContentPane().add(panelTitle);
        this.getContentPane().add(panelButtonsTop);
        this.getContentPane().add(panelButtonsBottom);

    }
}