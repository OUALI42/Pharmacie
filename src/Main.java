import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main {
    public static void main(String[] args) {

        Gson gson = new Gson();
        try (Reader reader = new FileReader("stocks_pharma.json")) {

            pharmacies Pharma = gson.fromJson(reader, pharmacies.class);

            Pharma.pharmacie.ShowProducts();
            Order order = new Order(Pharma.pharmacie);
            order.Order();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
