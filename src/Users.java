import java.lang.String;
import java.util.List;

public abstract class Users {
    List<String> names;
    List<String> passwords;

    public Users(List<String> names, List<String> passwords) {
        this.names = names;
        this.passwords = passwords;
    }

    /**
     * Method to Log in
     * <p>
     * This method take in argument a name and password
     * It then searches through the Users' names and passwords to see if one matches
     *
     * @return A CurrentUser Object with the name and rights of the user if the Username and password correspond (null if not)
     */
    public CurrentUser Login (String name, String password) {
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equals(name) && passwords.get(i).equals(password)) {
                System.out.println("You are logged in");
                return new CurrentUser(name, this);
            }
        }
        return null;
    }

    /**
     * Method to delete a user
     * <p>
     * This method take in argument a name
     * It then searches through the Users names
     * if one match, the name and the corresponding password are deleted from the Users' lists
     */
    public void DeleteUser(String name) {
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equals(name)) {
                System.out.println("User "+ names.get(i) +" was successfully deleted");
                names.remove(i);
                passwords.remove(i);
            }
        }
    }

    /**
     * Method to add a user
     * <p>
     * This method take in argument a name and a password
     * It then searches adds them to the Users' name and password lists
     */
    public void AddUser(String name, String password) {
        names.add(name);
        passwords.add(password);
    }
}
