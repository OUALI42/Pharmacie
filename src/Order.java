import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.String;
import java.util.Scanner;

public class Order extends Search {
    private Pharmacie pharmacy;

    public Order(Pharmacie pharmacy) {

        this.pharmacy = pharmacy;
    }

    public void Order () {
        // Create a search instance to look for products
        Search search = new Search(pharmacy);

        // Scanner objects for user input
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        Scanner scanner3 = new Scanner(System.in);
        Scanner scanner4 = new Scanner(System.in);

        // Loop to allow multiple interactions
        for (int j = 0; j < 10; j++) {
            System.out.println(" ");
            System.out.println("    \u001B[32m" + "Choose an option : " + "\u001B[0m");
            System.out.println("1\uFE0F⃣ . Order");
            System.out.println("2\uFE0F⃣ . Look at the stock");
            System.out.println("3\uFE0F⃣ . Exit");
            System.out.println(" ");

            // Read user scanner
            String option = scanner1.nextLine();
            System.out.println(" ");

            // If user selects "2" → Save pharmacy stock to a JSON file and display classification
            if (option.equalsIgnoreCase("2")) {
                Pharmacies pharmacys = new Pharmacies(pharmacy);
                Gson ProduitsGson = new Gson();

                // Save stock information into "stocks_pharma.json"
                try (Writer writer = new FileWriter("stocks_pharma.json")) {
                    ProduitsGson.toJson(pharmacys, writer);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                pharmacys.pharmacie.classification();
            }
            // If user selects "3" → Exit the ordering process
            else if (option.equalsIgnoreCase("3")) {
                return;
            }
            // If user selects "1" → Start an order
            else if (option.equalsIgnoreCase("1")) {
                System.out.println("What do you need ?");
                String pSearch = scanner2.nextLine();
                pSearch = pSearch.toLowerCase(); // Convert input to lowercase for case-insensitive search

                // Search for the product and get available quantity
                int nbProduct = search.Search(pSearch);

                // Ask if the user wants to order more products
                for (int i = 0; i < 10; i++) {
                    System.out.println("Do you need something else ?");
                    String answer1 = scanner3.nextLine();

                    // If user wants more products
                    if (answer1.toLowerCase().contains("yes") || answer1.toLowerCase().contains("yeah") || answer1.toLowerCase().contains("oui")) {
                        System.out.println("What do you need more ?");
                        String pSearch1 = scanner4.nextLine();
                        nbProduct = search.Search(pSearch1);
                    }

                    // If user says "no" and has selected a valid quantity, exit loop
                    else if (answer1.toLowerCase().contains("no") || answer1.toLowerCase().contains("non") && nbProduct > 0) {
                        break;
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
