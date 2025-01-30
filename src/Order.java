import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.String;
import java.util.ArrayList;
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

        for (int j = 0; j < 10; j++) {
            System.out.println(" ");
            System.out.println("    \u001B[32m" + "Choose an option : " + "\u001B[0m");
            System.out.println("1\uFE0F⃣ . Order");
            System.out.println("2\uFE0F⃣ . Look at the stock");
            System.out.println("3\uFE0F⃣ . Exit");
            System.out.println(" ");
            String option = scanner1.nextLine();
            System.out.println(" ");
            if (option.equalsIgnoreCase("2")) {
                Pharmacies pharmacys = new Pharmacies(pharmacy);
                Gson ProduitsGson = new Gson();

                try (Writer writer = new FileWriter("stocks_pharma.json")) {
                    ProduitsGson.toJson(pharmacys, writer);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                pharmacys.pharmacie.classification();
            } else if (option.equalsIgnoreCase("3")) {
                return;
            } else if (option.equalsIgnoreCase("1")) {
                System.out.println("What do you need ?");
                String pSearch = scanner2.nextLine();
                String nbProduct = search.Search(pSearch);

                String Commandes = " ";
                if (nbProduct != null) {
                    Commandes = "You have ordered " + pSearch;
                }

                for (int i = 0; i < 10; i++) {
                    System.out.println("Do you need something else ?");

                    String answer1 = scanner3.nextLine();
                    if (answer1.contains("yes") || answer1.contains("yeah") || answer1.contains("oui")) {
                        System.out.println("What do you need more ?");
                        String pSearch1 = scanner4.nextLine();
                        nbProduct = search.Search(pSearch1);

                        if (nbProduct != null) {
                            Commandes = Commandes + pSearch1;
                        }


                    } else if (answer1.contains("no") || answer1.contains("non")) {
                        System.out.println("Okay here your order, bye !");
                        System.out.println(Commandes);
                        break;
                    }
                }

                List<Inventory> productList = pharmacy.getProduits();
                for (Inventory p : productList) {
                    for (Product p1 : p.getProduits()) {
                        if (p1.nom.equals(pSearch) && p1.quantiteStock >= parseInt(nbProduct) && parseInt(nbProduct) >0){
                            p1.quantiteStock -= parseInt(nbProduct);
                            System.out.println("This product : " + pSearch + " has been ordered");
                        }
                    }
                }
                Pharmacies pharmacys = new Pharmacies(pharmacy);
                Gson ProduitsGson = new Gson();

                // Converts Java object to File
                try (Writer writer = new FileWriter("stocks_pharma.json")) {
                    ProduitsGson.toJson(pharmacys, writer);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
