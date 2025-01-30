public class CurrentUser {
    private final String name;
    Users user;

    CurrentUser(String name, Users user) {
        this.name = name;
        this.user = user;
    }

    public String getName() {
        return name;
    }
}
