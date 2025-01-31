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

    /**
     * Searches for a product in the inventory and processes the order if available.
     *
     * @param pSearch The name of the product to search for (case insensitive).
     * @return The quantity of the product ordered, or 0 if the product is not available or the order is invalid.
     */

    public int Search(String pSearch) {
        // Retrieve the list of products from the pharmacy inventory
        List<Inventory> productList = pharmacy2.getProduits();
        Scanner scanner2 = new Scanner(System.in);

        // Convert the search term to lowercase for case-insensitive comparison
        pSearch = pSearch.toLowerCase();

        boolean available = false; // Flag to check if the product is found

        // Iterate through inventory
        for (Inventory p : productList) {
            for (Product prod : p.getProduits()) {
                // Check if the product name contains the search term
                if (prod.nom.toLowerCase().contains(pSearch)) {
                    available = true;
                    System.out.println("We have " + prod.quantiteStock + " " + prod.nom + "s left.");
                    System.out.println("How many do you want?");

                    // Read the requested quantity from user input
                    String sc2 = scanner2.nextLine();

                    // Check if requested quantity is available
                    if (prod.quantiteStock < parseInt(sc2)) {
                        System.out.println("Sorry, we don't have enough of this product in stock. Come back another day or lower the quantity.");
                        break;
                    } else if (parseInt(sc2) <= 0) {
                        System.out.println("The quantity should be greater than zero");
                        break;
                    }

                    // Process the order if the quantity is valid
                    if (prod.quantiteStock >= parseInt(sc2) && parseInt(sc2) > 0) {
                        // Reduce the stock quantity by the requested amount
                        prod.quantiteStock -= parseInt(sc2);

                        // Print confirmation that the product has been ordered
                        System.out.println("This product: " + pSearch + " has been ordered");
                        return parseInt(sc2);
                    }
                }
            }
        }

        // If the product was not found in the inventory
        if (!available) {
            System.out.println("Sorry, we don't have this in stock");
        }

        return 0; // Return 0 if the product was not available or the order was invalid
    }
}