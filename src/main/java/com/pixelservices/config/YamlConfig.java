package com.pixelservices.config;

import com.pixelservices.logger.Logger;
import com.pixelservices.logger.LoggerFactory;
import org.simpleyaml.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * The YamlConfig class provides utility methods for loading, saving, and managing YAML configuration files.
 */
public class YamlConfig extends YamlConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(YamlConfig.class);
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
        this.filePath = path;
        this.file = new File(path);
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
        try (InputStream resourceStream = YamlConfig.class.getClassLoader().getResourceAsStream(filePath)) {
            if (resourceStream != null) {
                load(resourceStream);
            } else {
                logger.warn("Configuration file not found in JAR: " + filePath);
                loadFromString("");
            }
        } catch (IOException e) {
            logger.error("Failed to load configuration file from JAR: " + filePath, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads a YamlConfiguration from an existing file.
     */
    private void loadConfigFromFile() {
        try {
            load(file);
        } catch (IOException e) {
            logger.error("Failed to load configuration file: " + file.getPath(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves the configuration file to disk.
     */
    public void save() {
        try {
            if (!file.exists() && !file.createNewFile()) {
                logger.error("Failed to create configuration file: " + file.getPath());
                return;
            }
            save(file);
        } catch (IOException e) {
            logger.error("Failed to save configuration file: " + file.getPath(), e);
        }
    }
}