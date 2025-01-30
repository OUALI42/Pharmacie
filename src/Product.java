public class Product extends Inventory {
    int id;
    String nom;
    float prix;
    String quantite;
    String description;
    Product(int id, String nom, float prix, String quantite, String description) {
        //super();
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
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
        return Integer.parseInt(quantite);
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}