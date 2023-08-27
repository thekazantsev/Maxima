import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.Date;

import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.x509.X509V3CertificateGenerator;

import static java.security.KeyStore.getDefaultType;

/**
 * This class uses the BouncyCastle lightweight API to generate X.509 certificates programmatically.
 *
 * @author
 * based on https://github.com/abdulwaheed18/Self-Signed-Certificate/blob/master/src/com/waheed/create/certificate/SelfSignedCertificate.java
 *
 */

public class SelfSignedCertificate {
    private static final String CERTIFICATE_ALIAS = "certificate_alias_1";
    private static final String CERTIFICATE_ALGORITHM = "RSA";
    private static final String CERTIFICATE_DN = "CN=cn, O=o, L=L, ST=il, C= c";
    private static final String CERTIFICATE_NAME = "keystore.jks";
    private static final int CERTIFICATE_BITS = 4096;
    private static final String PASSWORD = "password";

    static {
        // adds the Bouncy castle provider to java security
        Security.addProvider(new BouncyCastleProvider());
    }

    @SuppressWarnings("deprecation")
    public X509Certificate createCertificate() throws Exception {
        X509Certificate cert;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(CERTIFICATE_ALGORITHM);
        keyPairGenerator.initialize(CERTIFICATE_BITS);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        // GENERATE THE X509 CERTIFICATE
        X509V3CertificateGenerator v3CertGen =  new X509V3CertificateGenerator();
        v3CertGen.setSerialNumber(BigInteger.valueOf(System.currentTimeMillis()));
        v3CertGen.setIssuerDN(new X509Principal(CERTIFICATE_DN));
        v3CertGen.setNotBefore(new Date(System.currentTimeMillis() - 1000L * 60 * 60 * 24));
        v3CertGen.setNotAfter(new Date(System.currentTimeMillis() + (1000L * 60 * 60 * 24 * 365*10)));
        v3CertGen.setSubjectDN(new X509Principal(CERTIFICATE_DN));
        v3CertGen.setPublicKey(keyPair.getPublic());
        v3CertGen.setSignatureAlgorithm("SHA256WithRSAEncryption");
        cert = v3CertGen.generateX509Certificate(keyPair.getPrivate());
        saveCert(cert,keyPair.getPrivate());
        return cert;
    }

    private void saveCert(X509Certificate cert, PrivateKey key) throws Exception {
        KeyStore keyStore = KeyStore.getInstance(getDefaultType());
        keyStore.load(null, PASSWORD.toCharArray());
        keyStore.setKeyEntry(CERTIFICATE_ALIAS, key, PASSWORD.toCharArray(),  new java.security.cert.Certificate[]{cert});
        File file = new File(".", CERTIFICATE_NAME);
        keyStore.store( new FileOutputStream(file), PASSWORD.toCharArray());
    }
}
