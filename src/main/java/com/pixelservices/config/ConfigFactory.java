package com.pixelservices.config;

public class ConfigFactory {
    public static YamlConfig getYamlConfig() {
        return new YamlConfig();
    }

    public static YamlConfig getYamlConfig(String path) {
        return new YamlConfig(path);
    }
}
