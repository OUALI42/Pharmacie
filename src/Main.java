import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Gson gson = new Gson();
        try (Reader reader = new FileReader("stocks_pharma.json")) {

            pharmacies Pharma = gson.fromJson(reader, pharmacies.class);

            System.out.println(Pharma.pharmacie.getProduits().get(0).produits.get(0).nom);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
