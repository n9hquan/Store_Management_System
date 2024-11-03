import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckProductController implements ActionListener {
    private CheckProductView checkProductView;

    public CheckProductController(CheckProductView checkProductView) {
        this.checkProductView = checkProductView;
        this.checkProductView.getBtnSearchProduct().addActionListener(this);
        this.checkProductView.getBtnViewAllProducts().addActionListener(this);
        this.checkProductView.getBtnReturn().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkProductView.getBtnSearchProduct()) {
            // Logic to search for a product can be implemented here
            // For example, you can open a search dialog or a new search view
            checkProductView.dispose(); // Close current view
            App.getInstance().getProductView().setVisible(true);
        } else if (e.getSource() == checkProductView.getBtnViewAllProducts()) {
            // Open the view all products screen
            checkProductView.dispose(); // Close current view
            App.getInstance().getViewAllProductsScreen().setVisible(true); // Assuming you have a method to get the ViewAllProducts screen
        } else if (e.getSource() == checkProductView.getBtnReturn()) {
            checkProductView.dispose(); // Close current view
            App.getInstance().getMainScreen().setVisible(true); // Return to the main menu
        }
    }
}
