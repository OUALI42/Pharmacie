import java.util.Comparator;
import java.util.List;

public class pharmacie {
    private String nom;
    private String adresse;
    private List<Inventory> produits;



    pharmacie(String nom, String adresse, List<Inventory> produits) {
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

//    void ShowProducts(){
//        produits.sort(Comparator.comparing(Inventory::getName));
//        for(Product p : produits){
//            System.out.println(p.Name+"/ Quantité = "+p.Quantity+"/ Price = "+p.Price+"/ Category = "+p.Category+"/ Subcategory ="+p.Subcategory);
//        }
//    }
}
