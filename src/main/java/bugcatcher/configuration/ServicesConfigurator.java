package bugcatcher.configuration;

import bugcatcher.helpers.Decryptor;
import java.util.Map;
import org.json.simple.JSONObject;

public class ServicesConfigurator {

    private final JSONObject jsonObject;

    private Decryptor decryptor;

    public ServicesConfigurator(JSONObject jsonObject, Decryptor decryptor) {
        this.decryptor = decryptor;
        this.jsonObject = jsonObject;
    }

    public String getAccessToken() {
        try {
            return getEncryptedData("accessToken");
        } catch (NullPointerException e) {
            throw new NullPointerException("Access token doesn't exists on configuration file");
        }
    }

    public String getHMac() {
        return getEncryptedData("hMac");
    }

    public String getPublicKey() {
        return getEncryptedData("publicKey");
    }

    public String getBaseUrl() {
        return jsonObject.get("baseUrl").toString();
    }

    public String getAccountId() {
        return getEncryptedData("accountId");
    }

    public String getPrivateKey() {
        return getEncryptedData("privateKey");
    }

    public Map<String, Object> getService(String service) {

        return (Map<String, Object>) ((Map<String, Object>) jsonObject.get("services")).get(service);
    }

    private String getEncryptedData(String key) {
        String encryptedData = jsonObject.get(key).toString();
        String decryptedData = decryptor.decrypt(encryptedData);
        return decryptedData;
    }

    public String getTenantId() {
        return getEncryptedData("tenantId");
    }

    public String getEncryptedDataFromService(String service, String key) {
        return decryptor.decrypt(getService(service).get(key).toString());
    }

    public String getDataFromService(String service, String key) {
        return getService(service).get(key).toString();
    }
}
