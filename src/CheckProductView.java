import javax.swing.*;
import java.awt.*;

public class CheckProductView extends JFrame {
    private JButton btnSearchProduct = new JButton("Search Product");
    private JButton btnViewAllProducts = new JButton("View All Products");
    private JButton btnReturn = new JButton("Return to Main Menu");

    public JButton getBtnSearchProduct() {
        return btnSearchProduct;
    }

    public JButton getBtnViewAllProducts() {
        return btnViewAllProducts;
    }

    public JButton getBtnReturn() {
        return btnReturn;
    }

    public CheckProductView() {
        // Basic frame setup
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Only close this window, not entire application
        this.setSize(400, 300);
        setLocationRelativeTo(null);

        // Set button sizes
        btnSearchProduct.setPreferredSize(new Dimension(200, 50));
        btnViewAllProducts.setPreferredSize(new Dimension(200, 50));
        btnReturn.setPreferredSize(new Dimension(200, 50));

        // Create and setup title panel
        JLabel title = new JLabel("Product Management");
        title.setFont(new Font("Sans Serif", Font.BOLD, 20));
        JPanel panelTitle = new JPanel();
        panelTitle.add(title);

        // Create panels for buttons
        JPanel panelSearchProduct = new JPanel();
        panelSearchProduct.add(btnSearchProduct);

        JPanel panelViewAllProducts = new JPanel();
        panelViewAllProducts.add(btnViewAllProducts);

        JPanel panelReturn = new JPanel();
        panelReturn.add(btnReturn);

        // Add panels to frame
        this.getContentPane().add(panelTitle);
        this.getContentPane().add(panelSearchProduct);
        this.getContentPane().add(panelViewAllProducts);
        this.getContentPane().add(panelReturn);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CheckProductView().setVisible(true);
        });
    }
}
