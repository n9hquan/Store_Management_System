import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerServiceController implements ActionListener {
    private CustomerServiceView customerServiceScreen;

    public CustomerServiceController(CustomerServiceView customerServiceScreen) {
        this.customerServiceScreen = customerServiceScreen;
        this.customerServiceScreen.getBtnRegister().addActionListener(this);
        this.customerServiceScreen.getBtnCheckInfo().addActionListener(this);
        this.customerServiceScreen.getBtnReturn().addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == customerServiceScreen.getBtnRegister()){
            customerServiceScreen.dispose();
            App.getInstance().getRegisterCustomerScreen().setVisible(true);
        }
        else if (e.getSource() == customerServiceScreen.getBtnCheckInfo()){
            customerServiceScreen.dispose();
            App.getInstance().getCustomerView().setVisible(true);
        }
        else if (e.getSource() == customerServiceScreen.getBtnReturn()){
            customerServiceScreen.dispose();
            App.getInstance().getMainScreen().setVisible(true);
        }
    }
}
