public class Product {
    int id;
    String nom;
    float prix;
    String quantiteStock;
    String description;
    Product(int id, String nom, float prix, int Quantity, String quantiteStock, String description) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.quantiteStock = quantiteStock;
        this.description = description;
    }
}