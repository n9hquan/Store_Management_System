
public class App {
    private static App instance;
    public LoginScreen loginScreen = new LoginScreen();
    public LoginController loginController;

    public static App getInstance() {
        if (instance == null) {
           instance = new App();
        }
  
        return instance;
    }
    private MainScreen mainScreen = new MainScreen();
    private CustomerServiceView CustomerServiceScreen = new CustomerServiceView();
    private RegisterCustomerView RegisterCustomerScreen = new RegisterCustomerView();
    private ViewAllProductsView viewAllProductsScreen = new ViewAllProductsView();
    private CheckProductView checkProductScreen = new CheckProductView();
    private OrderRecordsView orderRecordsScreen = new OrderRecordsView(null);
    private ProductView productScreen = new ProductView(null);
    private CustomerView customerScreen = new CustomerView(null);
    private OrderView orderScreen = new OrderView(null);
    private DataLayer dataLayer = new DataLayer();

    public MainScreenController mainScreenController;
    public CustomerServiceController customerServiceController;
    public CheckProductController checkProductController;
    public RegisterCustomerController registerCustomerController;
    public ProductController productController;
    public CustomerController customerController;
    public OrderController orderController;
    public OrderRecordsController orderRecordsController;

    public MainScreen getMainScreen() {
        return mainScreen;
    }
    public CustomerServiceView getCustomerServiceView(){
        return CustomerServiceScreen;
    }
    public LoginScreen getLoginScreen() {
        return loginScreen;
    }
    public RegisterCustomerView getRegisterCustomerScreen(){
        return RegisterCustomerScreen; 
    }
    public ViewAllProductsView getViewAllProductsScreen() {
        return viewAllProductsScreen; 
    }
    public CheckProductView getCheckProductView(){
        return checkProductScreen;
    }
    public ProductView getProductView(){
        return productScreen;
    }
    public CustomerView getCustomerView(){
        return customerScreen;
    }
    public OrderView getOrderView(){
        return orderScreen;
    }
    public OrderRecordsView getOrderRecordsView(){
        return orderRecordsScreen;
    }

    private App(){
        loginController = new LoginController(loginScreen);
        mainScreenController = new MainScreenController(mainScreen);
        customerServiceController = new CustomerServiceController(CustomerServiceScreen);
        checkProductController = new CheckProductController(checkProductScreen);
        registerCustomerController = new RegisterCustomerController(RegisterCustomerScreen);
        productController = new ProductController(productScreen);
        customerController = new CustomerController(customerScreen);
        orderController = new OrderController(orderScreen, dataLayer);
        orderRecordsController = new OrderRecordsController(orderRecordsScreen, dataLayer);
    }
    public static void main(String[] var0) {
        getInstance().getLoginScreen().setVisible(true);
    }
}