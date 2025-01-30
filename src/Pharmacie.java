import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Pharmacie {
    private String nom;
    private String adresse;
    private List<Inventory> produits;
    Order order;

    Pharmacie(String nom, String adresse, List<Inventory> produits) {
        this.nom = nom;
        this.adresse = adresse;
        this.produits = produits;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<Inventory> getProduits() {
        return produits;
    }

    public void setProduits(List<Inventory> produits) {
        this.produits = produits;
    }

//        public static void menu() {
//
//        Scanner sc = new Scanner(System.in);
//
//        System.out.println("1️⃣ Afficher l'inventaire");
//        System.out.println("2️⃣ Ajouter un produit");
//        System.out.println("3️⃣ Supprimer un produit");
//        System.out.println("4️⃣ Quitter");
//        System.out.print("👉 Choix : ");
//
//        int choix = sc.nextInt();
//
//        sc.nextLine();
//
//        if (choix == 1){
//
//        }
//
//        if (choix == 2){
//          Inventory.addProduct((Product) produits);
//        }
//
//        if (choix == 3){
//          Inventory.delProduct((Product) produits);
//        }
//
//        if (choix == 4){
//        }
//
//    }

    /**
     * Displays a list of all products sorted by name.
     * <p>
     * This method collects all products from the inventory list, sorts them alphabetically
     * by name, and then prints each product's details, including name, stock quantity,
     * price, category, and subcategory.
     */

    void ShowProducts() {
        // Create a list to store all products
        List<Product> AllProducts = new ArrayList<Product>();
        // Iterate through the inventory list and collect all products
        for (Inventory invent : produits) {
            for (Product p : invent.getProduits()) {
                AllProducts.add(p);
            }
        }
        // Sort the products alphabetically by name
        AllProducts.sort(Comparator.comparing(Product::getNom));
        // Print product details
        for (Product produit : AllProducts) {
            System.out.println(produit.nom + " // Quantité = " + produit.quantiteStock + " // Prix = " + produit.prix + " // Catégorie = " + produit.categorie + " // Sous-Catégorie = " + produit.sousCategorie);
        }
    }



    /**
     * Generates a warning message for products that have a low stock quantity.
     * <p>
     * This method iterates through all products in the inventory and checks if their stock
     * quantity is less than or equal to 30. If so, a warning message is appended to a
     * StringBuilder, listing the product names that are running low.
     *
     * @return A string containing warning messages for low-stock products.
     */

    public String warning_message() {
        // StringBuilder to construct the warning message efficiently
        StringBuilder message = new StringBuilder();

        // Iterate through the inventory list
        for (Inventory p : produits) {
            // Iterate through each product in the inventory
            for (Product p1 : p.getProduits()) {
                // Check if the stock quantity is too low
                if (p1.quantiteStock <= 30) {
                    message.append(p1.nom).append(" is too low!\n");
                }
            }
        }
        // Return the constructed warning message
        return message.toString();
    }



    /** This function shows the stock of our products ranked in an increasing way,
     * with a message if a product has a low quantity **/
    public void classification() {
        System.out.println("List of medications sorted by quantity:");
        List<Product> sortedList = new ArrayList<Product>(); // List temporary for tri
        for (Inventory p : produits) {
            sortedList.addAll(p.getProduits()); //Placement of products in the temporary list
        }
        for (int i = 1; i < sortedList.size(); i++) {
            Product key = sortedList.get(i); // is the location where Key should be placed
            int j = i - 1; // previous item

            while (j >= 0 && sortedList.get(j).quantiteStock > key.quantiteStock){
                sortedList.set(j + 1, sortedList.get(j)); // place the element to the right
                j--;
            }
            sortedList.set(j + 1, key); // is the location where Key should be placed
        }

        for (Product p : sortedList) {
            System.out.println("- Name: " + p.nom + " | Quantity: " + p.quantiteStock);
        }
        System.out.println("\n⚠️ Low stock products:");
        System.out.println(warning_message()); // Display of alert message
    }
}
