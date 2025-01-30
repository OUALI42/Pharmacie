import java.lang.String;
import java.util.List;

public class Pharmacist extends Users {

    public Pharmacist(List<String> namePharmacist, List<String> pwdPharmacist) {
        super(namePharmacist, pwdPharmacist);
    }

    @Override
    public void DeleteUser() {
        System.out.println("You don't have the required permissions");
    }


}
