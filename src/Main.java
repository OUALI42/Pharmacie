
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main {
    public static void main(String[] args) {

        Gson gson = new Gson();
        try (Reader reader = new FileReader("stocks_pharma.json")) {

            Pharmacies Pharma = gson.fromJson(reader, Pharmacies.class);

            Pharma.pharmacie.ShowProducts();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
