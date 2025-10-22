import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Base64;

public class PBKDF2Example {
    public static void main(String[] args) throws Exception {
        var password = "MySecret123!";
        var salt = "randomSalt".getBytes();
        int iterations = 100_000;
        int keyLength = 256;

        var spec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLength);
        var secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        var hash = secretKeyFactory.generateSecret(spec).getEncoded();

        System.out.println(Base64.getEncoder().encodeToString(hash));
    }
}
