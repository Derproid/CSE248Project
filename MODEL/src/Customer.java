/**
 * Created by Alex on 5/3/2017.
 */
public class Customer extends Account {

    String birthday;

    public Customer(String username, String password, String email, String address, String zip, String phone, String birthday) {
        super(username, password, email, address, zip, phone);
        this.birthday = birthday;
    }
}
