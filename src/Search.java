import java.util.List;

public class Search {
    private Pharmacy pharmacy;

    public Search(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    public void Search(String pSearch) {
        List<Product> productList = pharmacy.getProductList();

        for (Product p : productList) {
            if (p.getName().equalsIgnoreCase(pSearch)) {
                System.out.println("We have " + p.Quantity + " " + p.getName() + "s left.");
                break;
            }
            else System.out.println("We don't have this product in stock.");
            break;
        }
    }

}
