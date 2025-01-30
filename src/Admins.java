public class Admins extends Users {
    String nameAdmin;
    String pwdAdmin;
    public Admins(String nameAdmin, String pwdAdmin) {
        super();
        this.nameAdmin = nameAdmin;
        this.pwdAdmin = pwdAdmin;
    }

    public String getNameAdmin() {
        return nameAdmin;
    }

    public String getPwdAdmin() {
        return pwdAdmin;
    }

    public void login (String nameAdmin, String pwdAdmin) {

    }
}
