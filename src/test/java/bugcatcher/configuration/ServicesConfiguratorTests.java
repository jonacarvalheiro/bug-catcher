package bugcatcher.configuration;

import bugcatcher.helpers.Decryptor;
import java.util.HashMap;
import org.json.simple.JSONObject;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicesConfiguratorTests {

    private final JSONObject mockedJsonObject;
    private final ServicesConfigurator servicesConfiguration;
    private final JSONObject mockedServicesJsonObject;
    private Decryptor decryptor;

    public ServicesConfiguratorTests() {
        mockedJsonObject = new JSONObject();
        mockedServicesJsonObject = new JSONObject();
        decryptor = mock(Decryptor.class);
        servicesConfiguration = new ServicesConfigurator(mockedJsonObject, decryptor);
    }

    @Test
    public void getAccessTokenTest() {
        String expected = "mockedAccessToken";
        String key = "accessToken";

        mockedJsonObject.put(key, expected);
        when(decryptor.decrypt(anyString())).thenReturn(expected);
        assertThat(servicesConfiguration.getAccessToken()).isEqualTo(expected);
    }

    @Test
    public void getHMacTest() {
        String expected = "mockedhMac";
        String key = "hMac";

        mockedJsonObject.put(key, expected);
        when(decryptor.decrypt(anyString())).thenReturn(expected);
        assertThat(servicesConfiguration.getHMac()).isEqualTo(expected);
    }

    @Test
    public void getPublicKeyTest() {
        String expected = "mockedPublicKey";
        String key = "publicKey";

        mockedJsonObject.put(key, expected);
        when(decryptor.decrypt(anyString())).thenReturn(expected);
        assertThat(servicesConfiguration.getPublicKey()).isEqualTo(expected);
    }

    @Test
    public void getBaseUrlTest() {
        String expected = "mockedBaseUrl";
        String key = "baseUrl";

        mockedJsonObject.put(key, expected);
        when(decryptor.decrypt(anyString())).thenReturn(expected);
        assertThat(servicesConfiguration.getBaseUrl()).isEqualTo(expected);
    }

    @Test
    public void getTenantIdTest() {
        String expected = "mockedTenantId";
        String key = "tenantId";

        mockedJsonObject.put(key, expected);
        when(decryptor.decrypt(anyString())).thenReturn(expected);
        assertThat(servicesConfiguration.getTenantId()).isEqualTo(expected);
    }

    @Test
    public void getPrivateKeyTest() {
        String expected = "mockedPrivateKey";
        String key = "privateKey";

        mockedJsonObject.put(key, expected);
        when(decryptor.decrypt(anyString())).thenReturn(expected);
        assertThat(servicesConfiguration.getPrivateKey()).isEqualTo(expected);
    }

    @Test
    public void getAccountIdTest() {
        String expected = "mockedAccountId";
        String key = "accountId";

        mockedJsonObject.put(key, expected);
        when(decryptor.decrypt(anyString())).thenReturn(expected);
        assertThat(servicesConfiguration.getAccountId()).isEqualTo(expected);
    }

    @Test
    public void getServiceTest() {
        String mockedServiceName = "mockedServiceName";
        HashMap<String, String> service = new HashMap<>();
        mockedServicesJsonObject.put(mockedServiceName, service);
        mockedJsonObject.put("services", mockedServicesJsonObject);
        assertThat(servicesConfiguration.getService(mockedServiceName)).isEqualTo(service);
    }

    @Test
    public void getDataFromServiceTest() {
        String mockedServiceName = "mockedServiceName";
        HashMap<String, String> service = new HashMap<>();
        service.put("dummyKey","dummyData");
        mockedServicesJsonObject.put(mockedServiceName, service);
        mockedJsonObject.put("services", mockedServicesJsonObject);

        assertThat(servicesConfiguration.getDataFromService(mockedServiceName,"dummyKey")).isEqualTo("dummyData");
    }

    @Test
    public void getEncryptedDataFromServiceTest() {
        String mockedServiceName = "mockedServiceName";
        HashMap<String, String> service = new HashMap<>();
        service.put("dummyKey","dummyData");
        when(decryptor.decrypt(anyString())).thenReturn("dummyData");
        mockedServicesJsonObject.put(mockedServiceName, service);
        mockedJsonObject.put("services", mockedServicesJsonObject);

        assertThat(servicesConfiguration.getEncryptedDataFromService(mockedServiceName,"dummyKey")).isEqualTo("dummyData");
    }

}
