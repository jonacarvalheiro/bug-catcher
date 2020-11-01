package bugcatcher;

import bugcatcher.configuration.ServicesConfigurator;
import bugcatcher.helpers.Decryptor;
import bugcatcher.helpers.SystemEnvWrapper;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jayway.restassured.specification.RequestSpecification;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static com.jayway.restassured.RestAssured.given;

public class RestAssuredWrapper {

    private final ServicesConfigurator configurator;
    private RequestSpecification restClient;
    private Decryptor decryptor;
    private SystemEnvWrapper systemEnvWrapper;

    public RestAssuredWrapper() throws IOException, ParseException {
        systemEnvWrapper = new SystemEnvWrapper();
        decryptor = new Decryptor(systemEnvWrapper.loadEnvironmentVariable("ENCRYPTION_KEY"));
        configurator = makeConfigurator(decryptor);
    }

    public RestAssuredWrapper(ServicesConfigurator servicesConfigurator) {
        configurator = servicesConfigurator;
    }

    public RequestSpecification getRestClient() {
        if (restClient == null)
            restClient = makeRestClient();

        return restClient;
    }

    public ServicesConfigurator getConfigurator() {
        return configurator;
    }

    private RequestSpecification makeRestClient() {
        String accessToken = configurator.getAccessToken();
        return given().headers("Authorization", "bearer " +
            accessToken).when().config(com.jayway.restassured.RestAssured.config().encoderConfig(com.jayway.restassured.config.EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)));
    }

    private ServicesConfigurator makeConfigurator(Decryptor decryptor) throws IOException, ParseException {
        FileReader reader = new FileReader("config.json");
        JSONParser jsonParser = new JSONParser();
        return new ServicesConfigurator((JSONObject) jsonParser.parse(reader), decryptor);
    }

    public String parseObjectToJson(Object object) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().setDefaultSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY)).writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }


}
