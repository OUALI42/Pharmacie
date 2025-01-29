import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> ProductList = new ArrayList<>();
        ProductList.add(new Product("Doliprane",9.51f,55,"Médicament"));
        ProductList.add(new Product("Ibuprofen",14.94f,55,"Médicament"));
        Pharmacy pharmacy = new Pharmacy(ProductList);
        Search search = new Search(pharmacy);

        pharmacy.ShowProducts();

        search.Search("doliprane");
        search.Search("Ibuprofen");

    }
}
