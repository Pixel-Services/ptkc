package com.pixelservices.config;

import com.pixelservices.config.exception.ConfigException;
import com.pixelservices.config.exception.ConfigSaveException;
import org.simpleyaml.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * The YamlConfig class provides utility methods for loading, saving, and managing YAML configuration files.
 */
public class YamlConfig extends YamlConfiguration implements Config {
    private final String jarPath;
    private final String filePath;
    private final File file;

    /**
     * Constructs a YamlConfig instance of the default configuration file.
     */
    YamlConfig() {
        this("config.yml");
    }

    /**
     * Constructs a YamlConfig instance with a specified file path.
     *
     * @param path the path to the configuration file.
     */
    YamlConfig(String path) {
        this(path, path);
    }

    /**
     * Constructs a YamlConfig instance with a specified jar path and save path.
     *
     * @param jarPath  the path to the configuration file in the JAR.
     * @param filePath the path to the configuration file on disk.
     */
    YamlConfig(String jarPath, String filePath) {
        this.jarPath = jarPath;
        this.filePath = filePath;
        this.file = new File(filePath);
        if (file.exists()) {
            loadConfigFromFile();
        } else {
            loadConfigFromJar();
        }
    }

    /**
     * Loads a YamlConfiguration from the file in the JAR.
     */
    private void loadConfigFromJar() {
        try (InputStream resourceStream = YamlConfig.class.getClassLoader().getResourceAsStream(jarPath)) {
            if (resourceStream != null) {
                load(resourceStream);
            } else {
                loadFromString("");
            }
        } catch (IOException e) {
            throw new ConfigException("Failed to load configuration file from JAR", e);
        }
    }

    /**
     * Loads a YamlConfiguration from an existing file.
     */
    private void loadConfigFromFile() {
        try {
            load(file);
        } catch (IOException e) {
            throw new ConfigException("Failed to load configuration file", e);
        }
    }

    /**
     * Saves the configuration file to disk.
     */
    public void save() {
        try {
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists() && !parentDir.mkdirs()) {
                throw new ConfigSaveException("Failed to create directories for configuration file: " + filePath);
            }
            if (!file.exists() && !file.createNewFile()) {
                throw new ConfigSaveException("Failed to create configuration file: " + filePath);
            }
            super.save(file);
        } catch (IOException e) {
            throw new ConfigSaveException("Failed to save configuration file", e);
        }
    }
}