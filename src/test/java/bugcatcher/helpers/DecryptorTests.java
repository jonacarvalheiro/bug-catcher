package bugcatcher.helpers;


import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.NoSuchPaddingException;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.assertThrows;

public class DecryptorTests {


    private Decryptor decryptor;

    public DecryptorTests() {
        decryptor = new Decryptor("dfjisdlfksnjdkfgjoksçldf");
    }

    @Test
    public void decryptorInstance_KeyInferiorThan24Characters_ReturnInvalidArgumentException() throws InvalidKeySpecException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Decryptor("1234567890123456");
        });
        String expectedMessage = "Key must be superior to 24 characters!";
        String actualMessage = exception.getMessage();

        assertThat(actualMessage.contains(expectedMessage)).isTrue();
    }

    @Test
    public void decryptorInstance_KeyEqualTo24Characters_ReturnObject() {
        new Decryptor("123456789012345678901234");
    }

    @Test(expected = IllegalArgumentException.class)
    public void decrypt_InvalidInput_ReturnInvalidArgumentException() {
        decryptor.decrypt("");
    }

    @Test
    public void decrypt_ValidInput_ReturnDecryptedKey() {
        assertThat(decryptor.decrypt("xU653Wy/+Nw/hY4CENlOrtBbjcfC+hFQ")).isEqualTo("DummyPasswordToHash");
    }

    @Test
    public void encrypt_ValidInput_ReturnEncryptedKey() {
        assertThat(decryptor.encrypt("DummyPasswordToHash")).isEqualTo("xU653Wy/+Nw/hY4CENlOrtBbjcfC+hFQ");
    }
}
