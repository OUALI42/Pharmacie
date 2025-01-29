

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> Etudiants = new ArrayList<Product>();
        Etudiants.add(new Product("Doliprane",9.51f,55,"Médicament"));
        Etudiants.add(new Product("Efferalgan ",14.94f,4,"Médicament"));
        Etudiants.add(new Product("Ibuprofen",14.94f,40,"Médicament"));
        Etudiants.add(new Product("Amoxicilline ",14.94f,10,"Médicament"));
        Etudiants.add(new Product("Spasfon ",14.94f,5,"Médicament"));
        Etudiants.add(new Product("Aspirine ",14.94f,3,"Médicament"));


        Pharmacy MyPharmacy = new Pharmacy(Etudiants);

//        for(Product e : Etudiants ){
//            MyPharmacy.inventory(Etudiants);
//        }



        MyPharmacy.ShowProducts();
        MyPharmacy.classification();

    }
}
