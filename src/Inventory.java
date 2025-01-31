import java.util.List;

/**
 * Class Inventory containing a list of products.
 * Inventory is associated with a category and a sub-category.
 */
public class Inventory {
    public String categorie;
    public String sousCategorie;
    List<Product> produits;

    Inventory(){
    }

    /**
     * Constructor with parameters.
     * @param categorie The product category.
     * @param sousCategorie The product's sub-category.
     * @param produits The list of products associated with this category.
     */
    public Inventory(String categorie, String sousCategorie, List<Product> produits) {
        this.categorie = categorie;
        this.sousCategorie = sousCategorie;
        this.produits = produits;
    }

    /**
     * Defines product list.
     * @param produits New product list.
     */
    public void setProduits(List<Product> produits) {
        this.produits = produits;
    }

    /**
     * Returns the list of products contained in the inventory.
     * @return produits list.
     */
    public List<Product> getProduits() {
        return produits;
    }

}