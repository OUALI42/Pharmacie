import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Pharmacie {
    private String nom;
    private String adresse;
    private List<Inventory> produits;



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

    void ShowProducts(){
        List<Product> AllProducts = new ArrayList<Product>();
        for(Inventory invent : produits){
            AllProducts.addAll(invent.getProduits());
        }
        AllProducts.sort(Comparator.comparing(Product::getNom));

        for(Product produit : AllProducts) {
            System.out.println(produit.nom + " // Quantité = " + produit.quantite + " // Prix = " + produit.prix + " // Catégorie = " + produit.categorie + " // Sous-Catégorie =" + produit.sousCategorie);
        }
    }
}
