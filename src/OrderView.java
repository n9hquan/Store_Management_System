import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

class UneditableTableModel extends DefaultTableModel {
    public UneditableTableModel(Object[] columnNames, int rowCount) {
        super(columnNames, rowCount);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // All cells are uneditable
    }
}

public class OrderView extends JDialog {
    protected JTextField txtCustomerName; // Made protected
    protected JTextField txtOrderDate; // Made protected
    protected JTextField txtProductName; // Made protected
    protected JTextField txtQuantity; // Made protected
    private JButton btnAddOrder;
    private JButton btnDeleteOrder; // New Delete button
    private JButton btnSubmit; // New Submit button
    private JButton btnCancel;
    private JTable orderTable;
    private UneditableTableModel tableModel;
    private JLabel lblTotalOrderPrice; // Label for total order price

    public OrderView(Frame owner) {
        super(owner, "Make Order", false);
        setSize(600, 400);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 10, 10)); // Change to 7 rows for the new button

        inputPanel.add(new JLabel("Customer Name:"));
        txtCustomerName = new JTextField();
        inputPanel.add(txtCustomerName);

        inputPanel.add(new JLabel("Order Date:"));
        txtOrderDate = new JTextField();
        inputPanel.add(txtOrderDate);

        inputPanel.add(new JLabel("Product Name:"));
        txtProductName = new JTextField();
        inputPanel.add(txtProductName);

        inputPanel.add(new JLabel("Quantity:"));
        txtQuantity = new JTextField();
        inputPanel.add(txtQuantity);

        btnAddOrder = new JButton("Add Order");
        btnDeleteOrder = new JButton("Delete Order"); // Initialize Delete button
        btnSubmit = new JButton("Submit"); // Initialize Submit button
        btnCancel = new JButton("Return to Main Menu");

        inputPanel.add(btnAddOrder);
        inputPanel.add(btnDeleteOrder); // Add Delete button to the panel
        inputPanel.add(btnSubmit); // Add Submit button to the panel
        inputPanel.add(btnCancel);

        add(inputPanel, BorderLayout.NORTH);

        String[] columnNames = {"Customer Name", "Product Name", "Unit Price", "Quantity", "Total Price"};
        tableModel = new UneditableTableModel(columnNames, 0);
        orderTable = new JTable(tableModel);
        add(new JScrollPane(orderTable), BorderLayout.CENTER);

        // Create a label to display the total order price
        lblTotalOrderPrice = new JLabel("Total Order Price: $0.00");
        lblTotalOrderPrice.setHorizontalAlignment(SwingConstants.RIGHT);
        add(lblTotalOrderPrice, BorderLayout.SOUTH);
    }

    public String getCustomerName() {
        return txtCustomerName.getText();
    }

    public String getOrderDate() {
        return txtOrderDate.getText();
    }

    public String getProductName() {
        return txtProductName.getText();
    }

    public int getQuantity() {
        return Integer.parseInt(txtQuantity.getText());
    }

    public JButton getBtnAddOrder() {
        return btnAddOrder;
    }

    public JButton getBtnDeleteOrder() {
        return btnDeleteOrder; // Getter for the Delete button
    }

    public JButton getBtnSubmit() {
        return btnSubmit; // Getter for the Submit button
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public void addOrderToTable(String customerName, String productName, double unitPrice, int quantity, double totalPrice) {
        Object[] rowData = {customerName, productName, unitPrice, quantity, totalPrice};
        tableModel.addRow(rowData);
        updateTotalOrderPrice(); // Update the total price after adding an order
    }

    public void removeOrderFromTable(int rowIndex) {
        tableModel.removeRow(rowIndex);
        updateTotalOrderPrice(); // Update the total price after deleting an order
    }

    private void updateTotalOrderPrice() {
        double total = 0.0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            total += (double) tableModel.getValueAt(i, 4); // Assuming Total Price is in the 5th column
        }
        lblTotalOrderPrice.setText(String.format("Total Order Price: $%.2f", total));
    }

    public UneditableTableModel getTableModel() {
        return tableModel;
    }    

    public JTable getOrderTable() {
        return orderTable;
    }

    public void clearInputs() {
        txtProductName.setText("");
        txtQuantity.setText("");
    }
}
