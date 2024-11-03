import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

// Custom Table Model to make cells uneditable
class UneditableTableModel extends DefaultTableModel {
    // Constructor to match DefaultTableModel's constructor
    public UneditableTableModel(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount); // Call the superclass constructor
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // All cells are uneditable
    }
}

public class CustomerView extends JDialog {
    private JTextField txtCustomerID;
    private JTextField txtCustomerName;
    private JTextField txtPhoneNumber;
    private JTextField txtEmail;
    private JButton btnSearch;
    private JButton btnCancel;
    private JTable resultsTable;
    private UneditableTableModel tableModel;

    public JButton getBtnSearch() {
        return btnSearch;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public JTextField getTxtCustomerID() {
        return txtCustomerID;
    }

    public JTextField getTxtCustomerName() {
        return txtCustomerName;
    }

    public JTextField getTxtPhoneNumber() {
        return txtPhoneNumber;
    }

    public JTextField getTxtEmail() {
        return txtEmail;
    }

    public CustomerView(Frame owner) {
        super(owner, "Search for a Customer", false);
        setSize(600, 400);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        // Input fields panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        
        inputPanel.add(new JLabel("Customer ID:"));
        txtCustomerID = new JTextField();
        inputPanel.add(txtCustomerID);

        inputPanel.add(new JLabel("Customer Name:"));
        txtCustomerName = new JTextField();
        inputPanel.add(txtCustomerName);

        inputPanel.add(new JLabel("Phone Number:"));
        txtPhoneNumber = new JTextField();
        inputPanel.add(txtPhoneNumber);

        inputPanel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        inputPanel.add(txtEmail);

        btnSearch = new JButton("Search");
        btnCancel = new JButton("Cancel");
        inputPanel.add(btnSearch);
        inputPanel.add(btnCancel);

        add(inputPanel, BorderLayout.NORTH);

        // Results table
        String[] columnNames = {"Customer ID", "Name", "Phone Number", "Email"};
        tableModel = new UneditableTableModel(columnNames, 0);
        resultsTable = new JTable(tableModel);
        add(new JScrollPane(resultsTable), BorderLayout.CENTER);
    }

    public void displaySearchResults(List<Customer> customers) {
        // Clear previous results
        tableModel.setRowCount(0);

        // Populate table with search results
        for (Customer customer : customers) {
            Object[] rowData = {
                customer.getCustomerID(),
                customer.getCustomerName(),
                customer.getPhoneNumber(),
                customer.getEmail()
            };
            tableModel.addRow(rowData);
        }
    }
}
