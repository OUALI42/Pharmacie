import com.google.gson.Gson;

import java.io.*;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Gson PharmacistesGson = new Gson();
        try (Reader reader = new FileReader("stocks_pharma.json")) {

            List<String> PharmaNames = new ArrayList<String>();
            List<String> PharmaPwd = new ArrayList<String>();
            PharmaNames.add("Strauss");
            PharmaPwd.add("azerty123");
            PharmaNames.add("Liam");
            PharmaPwd.add("ULTRAKILL");

            Pharmacies Pharma = PharmacistesGson.fromJson(reader, Pharmacies.class);
            Order order = new Order(Pharma.pharmacie);
            order.Order();

            Pharmacist Pharmacists = new Pharmacist(PharmaNames, PharmaPwd);
//            Pharmacists.Login();

            String json = PharmacistesGson.toJson(Pharmacists);
            System.out.println(json);

            // Converts Java object to File
            try (Writer writer = new FileWriter("pharmacists.json")) {
                PharmacistesGson.toJson(Pharmacists, writer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
