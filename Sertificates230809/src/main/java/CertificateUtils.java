import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Enumeration;

import static java.security.KeyStore.getDefaultType;

public class CertificateUtils {

    public static void readAliases(String fileName, String password) {
        String keystoreFilename = fileName;
        char[] keystorePassword = password.toCharArray();

        try (FileInputStream fIn = new FileInputStream(keystoreFilename)) {
            KeyStore keystore = KeyStore.getInstance(getDefaultType());
            keystore.load(fIn, keystorePassword);
            Enumeration<String> aliases = keystore.aliases();
            while (aliases.hasMoreElements()) {
                String alias = aliases.nextElement();
                // Process each alias
                System.out.println("Alias: " + alias);
            }
        } catch (FileNotFoundException | CertificateException | KeyStoreException | NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readByAlias(String fileName, String password, String certAlias) {
        String keystoreFilename = fileName;
        char[] keystorePassword = password.toCharArray();

        try (FileInputStream fIn = new FileInputStream(keystoreFilename)) {
            KeyStore keystore = KeyStore.getInstance(getDefaultType());
            keystore.load(fIn, keystorePassword);
            Certificate cert = keystore.getCertificate(certAlias);
            System.out.println("Certificate content:\n" + cert);
        } catch (FileNotFoundException | CertificateException | KeyStoreException | NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
