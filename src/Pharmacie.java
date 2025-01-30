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

    void ShowProducts(){
        List<Product> AllProducts = new ArrayList<Product>();
        for(Inventory invent : produits){
            for(Product p : invent.getProduits()){
                AllProducts.add(p);
            }
        }

        AllProducts.sort(Comparator.comparing(Product::getNom));

        for(Product produit : AllProducts) {
            System.out.println(produit.nom + " // Quantité = " + produit.quantiteStock + " // Prix = " + produit.prix + " // Catégorie = " + produit.categorie + " // Sous-Catégorie =" + produit.sousCategorie);
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
}
