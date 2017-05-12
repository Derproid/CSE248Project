import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.naming.directory.InvalidAttributeIdentifierException;
import java.io.Serializable;

/**
 * Created by Alex on 5/3/2017.
 */
public class Account implements Serializable {

    private String username;    // stored as plaintext
    private String password;    // stored as SHA256
    private String email;
    private Profile profile;

    public Account(JsonObject jsonObject) throws InvalidAttributeIdentifierException {
        username = jsonObject.getString("username");
        password = jsonObject.getString("password");
        email = jsonObject.getString("email");
        String accountType = jsonObject.getString("accountType");
        if (accountType.equals("Customer")) {
            profile = new Customer();
        } else if (accountType.equals("Establishment")) {
            profile = new Establishment();
        } else {
            throw new InvalidAttributeIdentifierException();
        }
    }

    public String getUsername() {
        return username;
    }

    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        if (verifyPassword(oldPassword)) {
            this.password = newPassword;
            return true;
        } else {
            return false;
        }
    }

    public String getEmail() {
        return email;
    }

    public Profile getProfile() {
        return profile;
    }

    public JsonObject toJson() {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        jsonObjectBuilder.add("username", username);
        jsonObjectBuilder.add("password", password);
        jsonObjectBuilder.add("email", email);
        jsonObjectBuilder.add("accountType", profile.getClass().getName());
        jsonObjectBuilder.add("profile", profile.toJson());
        if (profile instanceof Customer) {
            jsonObjectBuilder.add("homepage", "homepage/");
        } else {
            jsonObjectBuilder.add("homepage", "homepage-establishment/");
        }
        return jsonObjectBuilder.build();
    }
}
