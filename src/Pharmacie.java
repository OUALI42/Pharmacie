import jdk.jfr.Category;

import java.util.*;
import java.util.ArrayList;

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

    void ShowProducts(){
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

    public String warning_message(){
        StringBuilder message = new StringBuilder();
        for (Inventory p : produits) {
            for (Product p1 : p.getProduits()) {
                if (p1.quantiteStock <= 30) {
                    message.append(p1.nom).append(" is too low!\n");
                }
            }
        }
        return message.toString();
    }


    public void classification() {
        System.out.println("List of medications sorted by quantity:");
        List<Product> sortedList = new ArrayList<Product>();
        for (Inventory p : produits) {
            sortedList.addAll(p.getProduits());
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
        System.out.println(warning_message());
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
            System.out.print("📝 Entrez le nom du produit : ");
            nom = sc.nextLine();

            System.out.print("💰 Entrez le prix du produit (Utilisez une virgule pour les décimales) : ");
            prix = (float)sc.nextFloat();
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

            if (nom!=null && quantite>0 && description!=null && categorie!=null && sousCategorie!=null) {

                List<Product> products = new ArrayList<>();
                Product product = new Product(7, nom, prix,quantite,description);
                products.add(product);
                Inventory AddInventory = new Inventory(categorie,sousCategorie,products);

                boolean NewInventory = true;
                for (Inventory p : produits) {
                    if (Objects.equals(p.categorie, categorie) && Objects.equals(p.sousCategorie, sousCategorie)) {
                        AddInventory = p;
                        NewInventory = false;
                    }
                }
                if (NewInventory) {
                    produits.add(AddInventory);
                }
                else {
                    AddInventory.getProduits().add(product);
                }

                System.out.println("✅ Produit ajouté avec succès !");
            }
            else {
                System.out.println("Incorrect value");
            }
        }
        else if (choix == 2) {
            RemoveProduct();
        }
        else {
            System.out.println("Incorrect input");
        }
    }

    public void RemoveProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nom du produit à retirer :");
        String nom = sc.nextLine();
        for (Inventory p : produits) {
            for (Product p1 : p.getProduits()) {
                if (Objects.equals(nom, p1.nom)) {
                    List<Product> newproducts = new ArrayList<>();
                    for (Product p2 : p.getProduits()) {
                        if (!Objects.equals(nom, p2.nom)) {
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
