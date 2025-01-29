public class Product extends Inventory {
    int id;
    String nom;
    float prix;
    String quantiteStock;
    String description;
    Product(int id, String nom, float prix, String quantiteStock, String description) {
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