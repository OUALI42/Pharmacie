import java.util.List;

public class Inventory {
    String categorie;
    String sousCategorie;
    List<Product> produits;
    public Inventory(String categorie, String sousCategorie, List<Product> produits) {
        this.categorie = categorie;
        this.sousCategorie = sousCategorie;
        this.produits = produits;
    }
}
