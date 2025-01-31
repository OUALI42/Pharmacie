import java.lang.String;

/**
 * Class representing a product in the inventory.
 * Inherits the Inventory class.
 */
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

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantiteStock;
    }

    public void setQuantite(int quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}