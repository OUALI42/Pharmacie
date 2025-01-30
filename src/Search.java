import java.lang.String;
import java.util.List;
import java.util.Scanner;

public class Search {
    private Pharmacie pharmacy2;

    public Search(Pharmacie pharmacy2) {
        this.pharmacy2 = pharmacy2;
    }

    protected Search() {
    }

    public String Search(String pSearch) {
        List<Inventory> productList = pharmacy2.getProduits();
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);

        boolean available = false;
        for (Inventory p : productList) {
            for(Product prod : p.getProduits()) {
                if (prod.nom.equalsIgnoreCase(pSearch)) {
                    available = true;
                    System.out.println("We have " + prod.quantiteStock + " " + prod.nom + "s left.");
                    System.out.println("Do you want to buy some ?");
                    for (int i = 0; i < 10; i++) {
                        String answer1 = scanner1.nextLine();
                        if (answer1.equalsIgnoreCase("yes")) {
                            System.out.println("How many ?");
                            String sc2 = scanner2.nextLine();
                            return sc2;
                        }
                    }
                }
            }
        }
        if (!available) System.out.println("Sorry, we don't have this in stock");
        return null;
    }
}