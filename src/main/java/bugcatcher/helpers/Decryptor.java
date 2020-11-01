package bugcatcher.helpers;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import org.apache.commons.codec.binary.Base64;

public class Decryptor {

    public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private static final String UNICODE_FORMAT = "UTF8";
    SecretKey key;
    private SecretKeyFactory skf;
    private byte[] arrayBytes;
    private DESedeKeySpec ks;
    private Cipher cipher;

    public Decryptor(String encryptionKey) {
        if (encryptionKey.length() < 24) {
            throw new IllegalArgumentException("Key must be superior to 24 characters!");
        }
        try {
            cipher = Cipher.getInstance(DESEDE_ENCRYPTION_SCHEME);
            arrayBytes = encryptionKey.getBytes(UNICODE_FORMAT);
            ks = new DESedeKeySpec(arrayBytes);
            skf = SecretKeyFactory.getInstance(DESEDE_ENCRYPTION_SCHEME);
            key = skf.generateSecret(ks);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return encryptedString;
    }

    public String decrypt(String encryptionKey) {
        if (encryptionKey.isEmpty()) {
            throw new IllegalArgumentException();
        }
        String decryptedText = null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedText = Base64.decodeBase64(encryptionKey);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText = new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return decryptedText;
    }
}
