package bugcatcher.helpers;


import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.Test;

public class SystemEnvWrapperTests {


    private SystemEnvWrapper systemEnvWrapper;

    public SystemEnvWrapperTests() {
        systemEnvWrapper = new SystemEnvWrapper();
    }
    @Test
    public void loadEnvironmentVariable_NotExistentEnvironmentVariable_ThrowException() {
        try {
            systemEnvWrapper.loadEnvironmentVariable("anyString");
        } catch (IllegalArgumentException illegalArgumentException) {
            AssertionsForClassTypes.assertThat(illegalArgumentException.getMessage()).isEqualTo("Environment Variable anyString is not defined !");
        }
    }
}
