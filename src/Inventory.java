import java.util.List;
import java.util.Scanner;

public class Inventory {
    String categorie;
    String sousCategorie;
    private List<Product> produits;

    public Inventory() {
        this.categorie = categorie;
        this.sousCategorie = sousCategorie;
        this.produits = produits;
    }

    public List<Product> getProduits() {
        return produits;
    }


    public void addProduct() {
        Scanner sc = new Scanner(System.in);

        System.out.println("1️⃣ Ajouter un produit");
        System.out.print("👉 Choix : ");
        int choix = sc.nextInt();
        sc.nextLine();

        String nom =null;
        float prix= 0f;
        int quantite=0;
        String description=null;

        if (choix == 1) {
            System.out.print("📝 Entrez le nom du produit : ");
            nom = sc.nextLine();

            System.out.print("💰 Entrez le prix du produit : ");
            prix = sc.nextFloat();
            if (prix <= 0) {
                System.out.println("⚠️ Le prix doit être supérieur ou égale 0 !");
                return;
            }

            System.out.print("📦 Entrez la quantité du produit : ");
            quantite = sc.nextInt();
            if (quantite <= 0) {
                System.out.println("⚠️ La quantité doit être supérieur ou égale à 0 !");
                return;
            }

            System.out.print("📂 Entrez la catégorie du produit : ");
            String categorie = sc.nextLine();

            System.out.print("📁 Entrez la sous-catégorie du produit : ");
            String sousCategorie = sc.nextLine();

            System.out.print("📝 Entrez la déscription du produit : ");
            description = sc.nextLine();
        }
        Product product = new Product(7, nom, prix,quantite,description);
        produits.add(product);
        System.out.println("✅ Produit ajouté avec succès !");

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