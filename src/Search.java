import java.lang.String;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Search {
    private Pharmacie pharmacy2;

    public Search(Pharmacie pharmacy2) {
        this.pharmacy2 = pharmacy2;
    }

    protected Search() {
    }

    public int Search(String pSearch) {
        List<Inventory> productList = pharmacy2.getProduits();
        Scanner scanner2 = new Scanner(System.in);
        pSearch = pSearch.toLowerCase();

        boolean available = false;
        for (Inventory p : productList) {
            for(Product prod : p.getProduits()) {
                if (prod.nom.toLowerCase().contains(pSearch)) {
                    available = true;
                    System.out.println("We have " + prod.quantiteStock + " " + prod.nom + "s left.");
                    System.out.println("How many do you want?");
                    String sc2 = scanner2.nextLine();
                    if (prod.quantiteStock < parseInt(sc2)) {
                        System.out.println("Sorry, we don't have enough of this product in stock come back another day or lower the quantity");
                        break;
                    } else if (parseInt(sc2) <= 0){
                        System.out.println("The quantity should be greater than zero");
                        break;
                    }
                    else return parseInt(sc2);
                }
            }
        }
        if (!available) System.out.println("Sorry, we don't have this in stock");
        return 0;
    }
}