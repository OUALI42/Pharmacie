import com.google.gson.Gson;

import java.io.*;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Gson gson = new Gson();
        try (Reader reader = new FileReader("stocks_pharma.json")) {

            Pharmacies Pharma = gson.fromJson(reader, Pharmacies.class);

            Pharma.pharmacie.ShowProducts();

            Order order = new Order(Pharma.pharmacie);
            order.Order();
            Pharma.pharmacie.classification();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Gson PharmacistesGson = new Gson();
        try (Reader reader = new FileReader("stocks_pharma.json")) {

            List<String> PharmaNames = new ArrayList<String>();
            List<String> PharmaPwd = new ArrayList<String>();
            PharmaNames.add("Strauss");
            PharmaPwd.add("azerty123");
            PharmaNames.add("Liam");
            PharmaPwd.add("ULTRAKILL");

            Pharmacist Pharmacists = new Pharmacist(PharmaNames, PharmaPwd);
//            Pharmacists.Login();
//            PharmacistesGson.toJson(reader, Pharmacist.class);

            String json = PharmacistesGson.toJson(Pharmacists);
            System.out.println(json);

            // Converts Java object to File
            try (Writer writer = new FileWriter("pharmacists.json")) {
                gson.toJson(Pharmacists, writer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
