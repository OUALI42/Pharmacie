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

    public CurrentUser Login (String name, String password) {
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equals(name) && passwords.get(i).equals(password)) {
                System.out.println("You are logged in");
                return new CurrentUser(name, this);
            }
        }
        return null;
    }

    public abstract void DeleteUser();
}
