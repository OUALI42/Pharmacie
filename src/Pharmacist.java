public class Pharmacist extends Users {
    String namePharmacist;
    String pwdPharmacist;
    public Pharmacist(String namePharmacist, String pwdPharmacist) {
        super();
        this.namePharmacist = namePharmacist;
        this.pwdPharmacist = pwdPharmacist;
    }

    public String getNamePharmacist() {
        return namePharmacist;
    }

    public void setNamePharmacist(String namePharmacist) {
        this.namePharmacist = namePharmacist;
    }

    public String getPwdPharmacist() {
        return pwdPharmacist;
    }

    public void setPwdPharmacist(String pwdPharmacist) {
        this.pwdPharmacist = pwdPharmacist;
    }

    public void login (String nameUser, String pwdUser) {

    }

    public void signUp () {

    }
}
