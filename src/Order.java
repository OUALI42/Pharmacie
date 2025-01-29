import java.util.Scanner;

public class Order {
    public void Order () {
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);

        System.out.println("Bonsoir !");
        scanner1.nextLine();

        System.out.println("Que souhaitez vous achetez :");
        String pName = scanner2.nextLine();
        System.out.println(pName);

    }
}
