package src;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> Etudiants = new ArrayList<Product>();
        Etudiants.add(new Product("Doliprane",9.51f,55,"Médicament"));
        Etudiants.add(new Product("Ibuprofen",14.94f,55,"Médicament"));
        Pharmacy MyPharmacy = new Pharmacy(Etudiants);
        MyPharmacy.ShowProducts();
    }
}
