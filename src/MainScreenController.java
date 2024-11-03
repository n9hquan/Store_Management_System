import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreenController implements ActionListener {
    private MainScreen mainScreen;

    public MainScreenController(MainScreen mainScreen) {
        this.mainScreen = mainScreen;
        this.mainScreen.getBtnMakeOrder().addActionListener(this);
        this.mainScreen.getBtnCheckProduct().addActionListener(this);
        this.mainScreen.getBtnCustomerService().addActionListener(this);
        this.mainScreen.getBtnOrderRecords().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainScreen.getBtnMakeOrder()){
            this.mainScreen.setVisible(false);
            App.getInstance().getOrderView().setVisible(true);
        }
        else if (e.getSource() == mainScreen.getBtnCheckProduct()){
            this.mainScreen.setVisible(false);
            App.getInstance().getCheckProductView().setVisible(true);
        }
        else if (e.getSource() == mainScreen.getBtnCustomerService()){
            this.mainScreen.setVisible(false);
            App.getInstance().getCustomerServiceView().setVisible(true);
        }
        else if (e.getSource() == mainScreen.getBtnOrderRecords()){
            this.mainScreen.setVisible(false);
            App.getInstance().getOrderRecordsView().setVisible(true);
        }
    }
}
