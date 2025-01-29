import java.util.List;

public class Inventory {
    String categorie;
    String sousCategorie;
    private List<Product> produits;
    public Inventory(String categorie, String sousCategorie, List<Product> produits) {
        this.categorie = categorie;
        this.sousCategorie = sousCategorie;
        this.produits = produits;
    }

    public Inventory() {
    }

    public List<Product> getProduits() {
        return produits;
    }



}
