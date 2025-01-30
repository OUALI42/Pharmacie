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

        System.out.println("Hello");
        scanner1.nextLine();

        System.out.println("What do you need ?");
        String pSearch = scanner2.nextLine();
        search.Search(pSearch);

        for (int i = 0; i < 10; i++) {
            System.out.println("Do you need something else ?");
            String answer1 = scanner3.nextLine();
            if (answer1.equalsIgnoreCase("yes")) {
                System.out.println("What do you need more ?");
                String pSearch1 = scanner4.nextLine();
                search.Search(pSearch1);
            } else if (!answer1.equalsIgnoreCase("yes")){
                System.out.println("Okay here your order, bye !");
                System.out.println("");
                break;
            }
        }
    }
}
