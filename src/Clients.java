import java.lang.String;
import java.util.List;

public class Clients extends Users {
    public Clients(List<String> namePharmacist, List<String> pwdPharmacist) {
        super(namePharmacist, pwdPharmacist);
    }

    @Override
    public void DeleteUser() {
        System.out.println("You don't have the required permissions");
    }
}

