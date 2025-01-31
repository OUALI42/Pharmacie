import java.util.List;
import java.util.Scanner;

public class Inventory {
    public String categorie;
    public String sousCategorie;
    List<Product> produits;

    Inventory(){
    }
    public Inventory(String categorie, String sousCategorie, List<Product> produits) {
        this.categorie = categorie;
        this.sousCategorie = sousCategorie;
        this.produits = produits;
    }

    public void setProduits(List<Product> produits) {
        this.produits = produits;
    }

    public List<Product> getProduits() {
        return produits;
    }

}