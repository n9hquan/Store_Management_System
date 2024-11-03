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

public class ProductView extends JDialog {
    private JTextField txtProductID;
    private JTextField txtProductName;
    private JTextField txtCategory;
    private JTextField txtUnitPrice;
    private JTextField txtSupplierID;
    private JButton btnSearch;
    private JButton btnCancel;
    private JTable resultsTable;
    private UneditableTableModel tableModel; // Use custom uneditable table model

    public JButton getBtnSearch() {
        return btnSearch;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public JTextField getTxtProductID() {
        return txtProductID;
    }

    public JTextField getTxtProductName() {
        return txtProductName;
    }

    public JTextField getTxtCategory() {
        return txtCategory;
    }

    public JTextField getTxtUnitPrice() {
        return txtUnitPrice;
    }

    public JTextField getTxtSupplierID() {
        return txtSupplierID;
    }

    public ProductView(Frame owner) {
        super(owner, "Search for a Product", false);
        setSize(600, 400);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        // Input fields panel
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        
        inputPanel.add(new JLabel("Product ID:"));
        txtProductID = new JTextField();
        inputPanel.add(txtProductID);

        inputPanel.add(new JLabel("Product Name:"));
        txtProductName = new JTextField();
        inputPanel.add(txtProductName);

        inputPanel.add(new JLabel("Category:"));
        txtCategory = new JTextField();
        inputPanel.add(txtCategory);

        inputPanel.add(new JLabel("Unit Price:"));
        txtUnitPrice = new JTextField();
        inputPanel.add(txtUnitPrice);

        inputPanel.add(new JLabel("Supplier ID:"));
        txtSupplierID = new JTextField();
        inputPanel.add(txtSupplierID);

        btnSearch = new JButton("Search");
        btnCancel = new JButton("Cancel");
        inputPanel.add(btnSearch);
        inputPanel.add(btnCancel);

        add(inputPanel, BorderLayout.NORTH);

        // Results table
        String[] columnNames = {"Product ID", "Name", "Category", "Unit Price", "Supplier ID"};
        tableModel = new UneditableTableModel(columnNames, 0); // Use custom table model
        resultsTable = new JTable(tableModel);
        add(new JScrollPane(resultsTable), BorderLayout.CENTER);
    }

    public void displaySearchResults(List<Product> products) {
        // Clear previous results
        tableModel.setRowCount(0);

        // Populate table with search results
        for (Product product : products) {
            Object[] rowData = {
                product.getProductID(),
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getSupplierID()
            };
            tableModel.addRow(rowData);
        }
    }
}
