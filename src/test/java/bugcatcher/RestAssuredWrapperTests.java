package bugcatcher;

import bugcatcher.configuration.ServicesConfigurator;
import com.jayway.restassured.specification.RequestSpecification;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RestAssuredWrapperTests {

    private ServicesConfigurator servicesConfiguration;
    private RestAssuredWrapper restAssuredWrapper;
    private RequestSpecification requestSpecification;

    public RestAssuredWrapperTests() throws IOException, ParseException {
        servicesConfiguration = mock(ServicesConfigurator.class);
        requestSpecification = mock(RequestSpecification.class);
        restAssuredWrapper = new RestAssuredWrapper(servicesConfiguration);
    }

    @Test
    public void getConfiguratorTest() {
        assertThat(restAssuredWrapper.getConfigurator()).isEqualTo(servicesConfiguration);
    }



}
