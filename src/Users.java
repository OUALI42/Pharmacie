import java.lang.String;
import java.util.List;
import java.util.Scanner;

public abstract class Users {
    List<String> names;
    List<String> passwords;

    public Users(List<String> names, List<String> passwords) {
        this.names = names;
        this.passwords = passwords;
    }

    public List<String> getNameList() {
        return names;
    }

    public List<String> getPasswordsList() {
        return passwords;
    }

    public Users () {
    }

    public void Login () {
        Scanner namesc = new Scanner(System.in);
        Scanner passwordsc = new Scanner(System.in);

        System.out.println("Username :");
        String name = namesc.nextLine();
        System.out.println("Password :");
        String password = passwordsc.nextLine();

        System.out.println(name);
        System.out.println(password);

        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equals(name) && passwords.get(i).equals(password)) {
                System.out.println("You are logged in");
            }
        }
    }
}
