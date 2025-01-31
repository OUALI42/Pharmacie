import java.util.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Class representing a pharmacy that manages a stock of products.
 * It contains information on its name, address and inventory.
 */
public class Pharmacie implements Stockable{
    private String nom;
    private String adresse;
    private List<Inventory> produits;
    Order order;

    Pharmacie(String nom, String adresse, List<Inventory> produits) {
        this.nom = nom;
        this.adresse = adresse;
        this.produits = produits;
    }

    public List<Inventory> getProduits() {
        return produits;
    }

    public void setProduits(List<Inventory> produits) {
        this.produits = produits;
    }

    /**
     * Displays a list of all products sorted by name.
     * <p>
     * This method collects all products from the inventory list, sorts them alphabetically
     * by name, and then prints each product's details, including name, stock quantity,
     * price, category, and subcategory.
     */
    public void ShowProducts(){
        // Sort products in each inventory alphabetically
        for(Inventory invent : produits){
            invent.getProduits().sort(Comparator.comparing(Product::getNom));
        }

        // Display products sorted by category
        for(Inventory invent : produits){
            System.out.println();
            System.out.println("\u001B[33m"+"Category | " + invent.categorie+" :"+"\u001B[0m");
            System.out.println();
            for(Product produit : invent.getProduits()) {
                System.out.println(produit.nom + " // Quantité = " + produit.quantiteStock + " // Prix = " + produit.prix + " // Catégorie = " + invent.categorie + " // Sous-Catégorie =" + invent.sousCategorie);
            }
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

    /**
     * Method for adding a product from stock.
     * Products are stored in a list.
     * The user can choose between adding and deleting a product.
     * If a product is added, several items of information are requested:
     * - Product name
     * - Price
     * - Quantity
     * - Description
     * - Category and sub-category
     */
    public void addProduct() {
        Scanner sc = new Scanner(System.in);

        // Display menu
        System.out.println("1️⃣ : Add a product");
        System.out.println("2️⃣ : Remove a product");
        System.out.print("👉 Choice : ");
        int choice = sc.nextInt();
        sc.nextLine();

        String name;
        float price;
        int quantity;
        String description;
        String category;
        String subCategory;

        if (choice == 1) {
            try {
                // Enter product information
                System.out.print("📝 Enter product name : ");
                name = sc.nextLine();

                System.out.print("💰 Enter product price (Use a comma for decimals) : ");
                price = (float)sc.nextFloat();
                if (price <= 0) {
                    System.out.println("⚠️ Price must be greater than or equal to 0!");
                }

                System.out.print("📦 Enter product quantity : ");
                quantity = sc.nextInt();
                if (quantity <= 0) {
                    System.out.println("⚠️ Quantity must be greater than or equal to 0!");
                }

                System.out.print("📂 Enter product category : ");
                Scanner sc3 = new Scanner(System.in);
                category = sc3.nextLine();

                System.out.print("📁 Enter product sub-category : ");
                Scanner sc2 = new Scanner(System.in);
                subCategory = sc2.nextLine();

                System.out.print("📝 Enter product description : ");
                description = sc.nextLine();

                // Check data entered
                if (name!=null && quantity>0 && description!=null && category!=null && subCategory!=null) {

                    List<Product> products = new ArrayList<>();
                    Product product = new Product(1, name, price,quantity,description);
                    products.add(product);
                    Inventory AddInventory = new Inventory(category,subCategory,products);

                    boolean NewInventory = true;
                    for (Inventory p : produits) {
                        if (Objects.equals(p.categorie, category) && Objects.equals(p.sousCategorie, subCategory)) {
                            AddInventory = p;
                            NewInventory = false;
                        }
                    }
                    if (NewInventory) {
                        produits.add(AddInventory);
                    }
                    else {
                        product.id = AddInventory.getProduits().size()+1;
                        AddInventory.getProduits().add(product);
                    }

                    System.out.println("✅ Product successfully added !");
                }
                else {
                    System.out.println("Incorrect value");
                }
            }
            catch (Exception e) {
                System.out.println("Incorrect value entered");
            }
        }
        else if (choice == 2) {
            // Call the method for deleting a product
            removeProduct();
        }
        else {
            System.out.println("Incorrect input");
        }
    }

    /**
     * Removes a Product object
     * <p>
     * This method asks the user for the name of a product to remove.
     * It then searches through the Inventories and the products and removes it if the name matches
     * If an inventory becomes empty, it's removed too
     */
    public void removeProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nom du produit à retirer :");
        String nom = sc.nextLine();
        // Browse inventory
        for (Inventory p : produits) {
            // Browse inventory products
            for (Product p1 : p.getProduits()) {
                if (Objects.equals(nom, p1.nom)) {
                    List<Product> newproducts = new ArrayList<>();
                    // Create a new product list without the product to be deleted
                    for (Product p2 : p.getProduits()) {
                        if (!Objects.equals(nom, p2.nom)) {
                            newproducts.add(p2);
                        }
                    }
                    // Update inventory with new product list
                    System.out.println("The product "+ nom + " was successfully removed !");
                    p.setProduits(newproducts);

                    // Check if inventory is now empty
                    if (p.getProduits().isEmpty()) {
                        List<Inventory> newInvents = new ArrayList<>();
                        for (Inventory p3 : produits) {
                            if (!Objects.equals(p3.categorie, p.categorie)) {
                                newInvents.add(p3);
                            }
                        }

                        System.out.println("The category " + p.categorie + " was empty so it was deleted too!");
                        setProduits(newInvents);
                    }
                }
            }
        }
    }
}
