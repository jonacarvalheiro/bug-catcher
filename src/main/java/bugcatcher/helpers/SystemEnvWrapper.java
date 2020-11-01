package bugcatcher.helpers;

public class SystemEnvWrapper {


    public String loadEnvironmentVariable(String environmentVariable) {

        String value = System.getenv(environmentVariable);
        if (value == null) {
            throw new IllegalArgumentException("Environment Variable " + environmentVariable + " is not defined !");
        }
        return value;
    }

}
