package properties;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class DatabaseConfiguration {

    private Properties config;

    public DatabaseConfiguration(File file) {
        try (
                FileInputStream fileInputStream = new FileInputStream(file)
        ) {
            load(fileInputStream);
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot read file", ioe);
        }
    }

    public DatabaseConfiguration() {
        try (
                InputStream resource = DatabaseConfiguration.class.getResourceAsStream("/db.properties")
        ) {
            load(resource);
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read file from classpath", ioe);
        }
    }

    private Properties load(InputStream inputStream) {
        config = new Properties();
        try (
                InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)
        ) {
            config.load(reader);
            return config;
        } catch (IOException ioe) {
            throw new IllegalStateException("Cannot read properties file from inputstream", ioe);
        }
    }

    public String getHost() {
        return config.getProperty("db.host");
    }

    public int getPort() {
        return Integer.parseInt(config.getProperty("db.port"));
    }

    public String getSchema() {
        return config.getProperty("db.schema");
    }
}
