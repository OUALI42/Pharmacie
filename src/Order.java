import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.String;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Order extends Search {
    private Pharmacie pharmacy;


    public Order(Pharmacie pharmacy) {

        this.pharmacy = pharmacy;
    }


    public void Order () {
        Search search = new Search(pharmacy);

        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        Scanner scanner3 = new Scanner(System.in);
        Scanner scanner4 = new Scanner(System.in);

        System.out.println("Hello");
        scanner1.nextLine();

        System.out.println("What do you need ?");
        String pSearch = scanner2.nextLine();
        String nbProduct = search.Search(pSearch);

        String Commandes = "Vous avez commandé : ";
        if (nbProduct != null) {
            Commandes = Commandes+" "+pSearch;
        }


        for (int i = 0; i < 10; i++) {
            System.out.println("Do you need something else ?");

            String answer1 = scanner3.nextLine();
            if (answer1.equalsIgnoreCase("yes")) {
                System.out.println("What do you need more ?");
                String pSearch1 = scanner4.nextLine();
                nbProduct = search.Search(pSearch1);
                if (nbProduct != null) {
                    Commandes = Commandes + pSearch1;
                }

            } else if (!answer1.equalsIgnoreCase("yes")){
                System.out.println("Okay here your order, bye !");
                System.out.println(Commandes);
                break;
            }
        }
        List<Inventory> productList = pharmacy.getProduits();
        for (Inventory p : productList){
            for (Product p1 : p.getProduits()){
                if(p1.nom.equals(pSearch) && p1.quantiteStock >= parseInt(nbProduct)){
                    p1.quantiteStock -= parseInt(nbProduct);
                    System.out.println("This product : " + pSearch  + "has been ordered");
                }
            }
        }
        Pharmacies pharmacys = new Pharmacies(pharmacy);
        Gson ProduitsGson = new Gson();

        String json = ProduitsGson.toJson(pharmacys);
        System.out.println(json);

        // Converts Java object to File
        try (Writer writer = new FileWriter("stocks_pharma.json")) {
            ProduitsGson.toJson(pharmacys, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
