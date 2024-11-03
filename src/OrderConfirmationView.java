import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class OrderConfirmationView extends JDialog {
    private JLabel lblCustomerName;
    private JLabel lblOrderDate;
    private JTable orderTable;
    private DefaultTableModel tableModel;

    public OrderConfirmationView(Frame owner, String customerName, String orderDate, UneditableTableModel orderTableModel) {
        super(owner, "Order Confirmation", true);
        setSize(600, 400);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        // Customer Name
        lblCustomerName = new JLabel("Customer Name: " + customerName);
        lblOrderDate = new JLabel("Order Date: " + orderDate);
        
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.add(lblCustomerName);
        infoPanel.add(lblOrderDate);
        add(infoPanel, BorderLayout.NORTH);

        // Table for Order Details
        String[] columnNames = {"Customer Name", "Product Name", "Unit Price", "Quantity", "Total Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        orderTable = new JTable(tableModel);
        add(new JScrollPane(orderTable), BorderLayout.CENTER);

        // Populate the order table with data from the OrderView
        populateOrderTable(orderTableModel);

        // Add OK button to close the dialog
        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(e -> dispose());
        add(btnOk, BorderLayout.SOUTH);
    }

    private void populateOrderTable(UneditableTableModel orderTableModel) {
        for (int i = 0; i < orderTableModel.getRowCount(); i++) {
            Object[] rowData = new Object[orderTableModel.getColumnCount()];
            for (int j = 0; j < orderTableModel.getColumnCount(); j++) {
                rowData[j] = orderTableModel.getValueAt(i, j);
            }
            tableModel.addRow(rowData);
        }
    }
}
