import java.util.Scanner;

public class Order extends Search {
    private Pharmacy pharmacy;

    public Order(Pharmacy pharmacy) {

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

        System.out.println("Do you need something else ?");
        for (int i = 0; i < 10; i++) {
            String answer1 = scanner3.nextLine();
            if (answer1.equalsIgnoreCase("yes")) {
                System.out.println("What do you need more ?");
                String pSearch1 = scanner4.nextLine();
                search.Search(pSearch1);
            } else if (!answer1.equalsIgnoreCase("yes")){
                System.out.println("Okay here your order, bye !");
            }
        }
    }
}
