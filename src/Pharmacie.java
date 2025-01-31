import jdk.jfr.Category;

import java.util.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

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
        for(Inventory invent : produits){
            invent.getProduits().sort(Comparator.comparing(Product::getNom));
        }

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


    public void addProduct() {
        Scanner sc = new Scanner(System.in);

        System.out.println("1️⃣ : Ajouter un produit");
        System.out.println("2️⃣ : Retirer un produit");
        System.out.print("👉 Choix : ");
        int choix = sc.nextInt();
        sc.nextLine();

        String nom;
        float prix;
        int quantite;
        String description;
        String categorie;
        String sousCategorie;

        if (choix == 1) {
            try {
                System.out.print("📝 Entrez le nom du produit : ");
                nom = sc.nextLine();

                System.out.print("💰 Entrez le prix du produit (Utilisez une virgule pour les décimales) : ");
                prix = sc.nextFloat();
                if (prix <= 0) {
                    System.out.println("⚠️ Le prix doit être supérieur ou égale 0 !");
                }

                System.out.print("📦 Entrez la quantité du produit : ");
                quantite = sc.nextInt();
                if (quantite <= 0) {
                    System.out.println("⚠️ La quantité doit être supérieur ou égale à 0 !");
                }

                System.out.print("📂 Entrez la catégorie du produit : ");
                Scanner sc3 = new Scanner(System.in);
                categorie = sc3.nextLine();

                System.out.print("📁 Entrez la sous-catégorie du produit : ");
                Scanner sc2 = new Scanner(System.in);
                sousCategorie = sc2.nextLine();

                System.out.print("📝 Entrez la déscription du produit : ");
                description = sc.nextLine();

                if (nom != null && quantite > 0 && description != null && categorie != null && sousCategorie != null) {

                    List<Product> products = new ArrayList<>();
                    Product product = new Product(7, nom, prix, quantite, description);
                    products.add(product);
                    Inventory AddInventory = new Inventory(categorie, sousCategorie, products);

                    boolean NewInventory = true;
                    for (Inventory p : produits) {
                        if (Objects.equals(p.categorie, categorie) && Objects.equals(p.sousCategorie, sousCategorie)) {
                            AddInventory = p;
                            NewInventory = false;
                        }
                    }
                    if (NewInventory) {
                        produits.add(AddInventory);
                    } else {
                        AddInventory.getProduits().add(product);
                    }

                    System.out.println("✅ Produit ajouté avec succès !");
                } else {
                    System.out.println("Incorrect value");
                }
            }
            catch (Exception e) {
                System.out.println("Incorrect value entered");
            }
        }
        else if (choix == 2) {
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
        String nom = sc.nextLine().toLowerCase();
        for (Inventory p : produits) {
            for (Product p1 : p.getProduits()) {
                if (p1.nom.toLowerCase().contains(nom.toLowerCase())) {
                    List<Product> newproducts = new ArrayList<>();
                    for (Product p2 : p.getProduits()) {
                        if (!p1.nom.toLowerCase().contains(nom.toLowerCase())) {
                            newproducts.add(p2);
                        }
                    }
                    System.out.println("The product "+ nom + " was successfully removed !");
                    p.setProduits(newproducts);

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
