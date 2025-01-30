import java.util.List;

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
}
