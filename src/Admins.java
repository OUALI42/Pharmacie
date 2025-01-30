import java.lang.String;
import java.util.List;
import java.util.List;
import java.util.Scanner;

public class Admins extends Users {
    Users users;
    public Admins(List<String> nameAdmin, List<String> pwdAdmin) {
        super(nameAdmin, pwdAdmin);
    }

    @Override
    public void DeleteUser() {
        Scanner Deletesc = new Scanner(System.in);
        System.out.println("Username of user to delete :");
        String name = Deletesc.nextLine();
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equals(name)) {
                names.remove(i);
                passwords.remove(i);
            }
        }
    }

}
