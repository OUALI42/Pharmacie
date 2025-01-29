import java.util.List;
import java.util.Scanner;

public class Search {
    private Pharmacy pharmacy;

    public Search(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

    protected Search() {
    }

    public void Search(String pSearch) {
        List<Product> productList = pharmacy.getProductList();
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);

        for (Product p : productList) {
            if (p.getName().equalsIgnoreCase(pSearch)) {
                System.out.println("We have " + p.Quantity + " " + p.getName() + "s left.");
                System.out.println("Do you want to buy some ?");
                for (int i = 0; i < 10; i++) {
                    String answer1 = scanner1.nextLine();
                    if (answer1.equalsIgnoreCase("yes")) {
                        System.out.println("How many ?");
                        scanner2.nextLine();
                        break;
                    }
                }
                break;
            }
            else System.out.println("Sorry, we don't have this product in stock.");
            break;
        }
    }

}
