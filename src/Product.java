import java.lang.String;

public class Product extends Inventory {
    int id;
    String nom;
    float prix;
    int quantiteStock;
    String description;
    Product(int id, String nom, float prix, int quantiteStock, String description) {
        super();
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.quantiteStock = quantiteStock;
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

}