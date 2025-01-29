import java.util.List;
import java.util.Scanner;

public class Search {
    private pharmacie pharmacy;

    public Search(pharmacie pharmacy) {
        this.pharmacy = pharmacy;
    }

    protected Search() {
    }

    public void Search(String pSearch) {
        List<Inventory> productList = pharmacy.getProduits();
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);

        for (Inventory p : productList) {
            for(Product prod : p.getProduits()) {
                if (prod.nom.equalsIgnoreCase(pSearch)) {
                    System.out.println("We have " + prod.quantiteStock + " " + prod.nom + "s left.");
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
            }
            break;
        }
        System.out.println("Sorry, we don't have this product in stock.");
    }

}
