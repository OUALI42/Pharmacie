import java.util.List;
import java.util.Scanner;

public class Inventory {
    String categorie;
    String sousCategorie;
    private static List<Product> produits;

    public Inventory() {
        this.categorie = categorie;
        this.sousCategorie = sousCategorie;
        this.produits = produits;
    }

    public List<Product> getProduits() {
        return produits;
    }


    public static void addProduct(Product product) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1️⃣ Afficher l'inventaire");
        System.out.print("👉 Choix : ");
        int choix = sc.nextInt();
        sc.nextLine();

        if (choix == 1) {
            System.out.print("📝 Entrez le nom du produit : ");
            String nom = sc.nextLine();

            System.out.print("💰 Entrez le prix du produit : ");
            String prix = sc.nextLine();
            Float prixEnChiffre = Float.valueOf(prix);
            if (prixEnChiffre <= 0) {
                System.out.println("⚠️ Le prix doit être supérieur ou égale 0 !");
            }

            System.out.print("📦 Entrez la quantité du produit : ");
            String quantite = sc.nextLine();
            Integer quantiteEnChiffre = Integer.valueOf(quantite);
            if (quantiteEnChiffre <= 0) {
                System.out.println("⚠️ La quantité doit être supérieur ou égale à 0 !");
            }

            System.out.print("📂 Entrez la catégorie du produit : ");
            String categorie = sc.nextLine();

            System.out.print("📁 Entrez la sous-catégorie du produit : ");
            String sousCategorie = sc.nextLine();

            System.out.print("📝 Entrez la déscription du produit : ");
            String description = sc.nextLine();
        }

        produits.add(product);
        System.out.println("✅ Produit ajouté avec succès !");

        sc.close();
    }

//    public static void delProduct(Product product) {
//
//        Scanner sc = new Scanner(System.in);
//        int choix = sc.nextInt();
//
//        sc.nextLine();
//
//        if (choix == 3){
//
//        }
//
//    }

}