
import java.security.cert.X509Certificate;

/**
 * Програмно создать X.509 сертификат и добавить его в KeyStore
 *
 */

public class Main {
    public static void main(String[] args) throws Exception {
        SelfSignedCertificate signedCertificate = new SelfSignedCertificate();
        X509Certificate certificate = signedCertificate.createCertificate();
        CertificateUtils.readAliases("keystore.jks", "password");
        CertificateUtils.readByAlias("keystore.jks", "password", "certificate_alias_1");


        //TODO: Try CertificateFactory instance = CertificateFactory.getInstance("X.509");


    }
}
