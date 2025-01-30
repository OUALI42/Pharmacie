import java.lang.String;
import java.util.Scanner;

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


            System.out.println("Choose an option :");
            System.out.println("1 - Order");
            System.out.println("2 - Look at the stock");
            String op1 = scanner1.nextLine();
            if (op1.equalsIgnoreCase("1")) {

            } else if (op1.equalsIgnoreCase("2")) {
                pharmacy.;
                break;
            }

            System.out.println("What do you need ?");
            String pSearch = scanner2.nextLine();
            boolean Available = search.Search(pSearch);

            String Commandes = "Vous avez commandé : ";
            System.out.println(Available);
            if (Available) {
                Commandes = Commandes + " " + pSearch;
            }


            for (int i = 0; i < 10; i++) {
                System.out.println("Do you need something else ?");

                String answer1 = scanner3.nextLine();
                if (answer1.equalsIgnoreCase("yes")) {
                    System.out.println("What do you need more ?");
                    String pSearch1 = scanner4.nextLine();
                    Available = search.Search(pSearch1);
                    System.out.println(Available);
                    if (Available) {
                        Commandes = Commandes + pSearch1;
                    }

                } else if (!answer1.equalsIgnoreCase("yes")) {
                    System.out.println("Okay here your order, bye !");
                    System.out.println(Commandes);
                    break;
                }
            }
        }
    }
}
