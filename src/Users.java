import java.util.List;

public abstract class Users {
    List<Admins> adminsList;
    List<Pharmacist> pharmacistList;

    public Users(List<Admins> adminsList, List<Pharmacist> pharmacistList) {
        this.adminsList = adminsList;
        this.pharmacistList = pharmacistList;
    }

    public List<Admins> getAdminsList() {
        return adminsList;
    }

    public List<Pharmacist> getPharmaciensList() {
        return pharmacistList;
    }

    public Users () {
    }
}
