package org.acme.configs;

import io.quarkus.runtime.configuration.ProfileManager;
import org.eclipse.microprofile.config.spi.ConfigSource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertyConfiguration implements ConfigSource {
    private String activeProfile;
    private final Map<String, String> properties = new HashMap<>();

    /**
     * Loading configs takes priority by the weight of this number.
     * <p>
     * It should be lower than 300, otherwise it will have higher priority than env variables.
     */
    private static final int HIGHEST_PRIO_FOR_FILES_LOWER_THAN_ENV = 299;

    @Override
    public int getOrdinal() {
        return HIGHEST_PRIO_FOR_FILES_LOWER_THAN_ENV;
    }

    @Override
    public Map<String, String> getProperties() {
        init();

        return properties;
    }

    @Override
    public String getValue(String propertyName) {
        return properties.get(propertyName);
    }

    @Override
    public String getName() {
        return "application-" + activeProfile;
    }

    private void init() {
        if (!properties.isEmpty()) {
            return;
        }

        activeProfile = ProfileManager.getActiveProfile();

        var propertiesToLoad = new Properties();
        var name = "application-" + activeProfile + ".properties";

        try (var inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(name)) {
            propertiesToLoad.load(inStream);
        } catch (IOException ex) {
            throw new RuntimeException("error loading configs", ex);
        }

        for (var key : propertiesToLoad.stringPropertyNames()) {
            properties.put(key, propertiesToLoad.getProperty(key));
        }
    }
}
