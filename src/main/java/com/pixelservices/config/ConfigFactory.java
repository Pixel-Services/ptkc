package com.pixelservices.config;

/**
 * The ConfigFactory class provides factory methods for creating configuration objects.
 */
public class ConfigFactory {

    /**
     * Returns a new YamlConfig instance of the default configuration file.
     *
     * @return a new YamlConfig instance.
     */
    public static YamlConfig getYamlConfig() {
        return new YamlConfig();
    }

    /**
     * Returns a new YamlConfig instance with a specified file path.
     *
     * @param path the path to the configuration file.
     * @return a new YamlConfig instance.
     */
    public static YamlConfig getYamlConfig(String path) {
        return new YamlConfig(path);
    }
}
