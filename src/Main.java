import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main {
    public static void main(String[] args) {

        Gson gson = new Gson();
        try (Reader reader = new FileReader("stocks_pharma.json")) {

            Pharmacies Pharma = gson.fromJson(reader, Pharmacies.class);

//            Pharma.pharmacie.ShowProducts();
            Pharma.pharmacie.classification();
            Pharma.pharmacie.update_of_command("Tramadol",10);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}