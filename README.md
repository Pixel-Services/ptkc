# ptkc 🛠️
Ptkc is a library for configuration files. It was previously part of the [PTK (Pixel Tool Kit)](https://github.com/Pixel-Services/PixelToolkit) library, but has been separated into its own library for easier use and maintenance. 

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven

## Installation
To include ptkc in your project, add the following dependency and repository to your ``pom.xml``:

### Dependency
```xml
<dependency>
    <groupId>com.pixelservices.logger</groupId>
    <artifactId>ptkc</artifactId>
    <version>${ptkcversion}</version>
</dependency>
```
### Repository
```xml
<repository>
    <id>pixel-services-releases</id>
    <name>Pixel Services</name>
    <url>https://maven.pixel-services.com/releases</url>
</repository>
```

## Usage
### Loading and Saving
Here is an example of how to use ptkc to load a Yaml configuration file and get a value from it.
If (In this example) you would add a file named `config.yml` to your compiled jar, ptkc would automatically load it, otherwise it would create one for you.
By running `YamlConfig#save()` it will save the configuration to a file outside of the jar. That way a user can edit the configuration. If a external file is present, it will load that instead of the one in the jar.
```java
import com.pixelservices.config.ConfigFactory;
import com.pixelservices.config.YamlConfig;

public class Example {
    public static void main(String[] args) {
        YamlConfig config = ConfigFactory.getYamlConfig("config.yml");
  
        config.save();
    }
}
```
### Reading and Editing
By using `YamlConfig#getString()` you can get a String value from the configuration file. Similarly, you can use `YamlConfig#getInt()` to get an integer value. 
To edit the configuration file, you can use `YamlConfig#set(key, value)` to set a value.
```java
import com.pixelservices.config.ConfigFactory;
import com.pixelservices.config.YamlConfig;

public class Example {
    public static void main(String[] args) {
        YamlConfig config = ConfigFactory.getYamlConfig("config.yml");
  
        String value = config.getString("key");
        
        String newValue = "new value";
        
        config.set("key", newValue);
    }
}
```


## Contributing
We welcome contributions! To contribute to ptkc:
1. Fork the repository: [ptkc on GitHub](https://github.com/Pixel-Services/ptkc)
2. Create a feature branch: `git checkout -b feature-name`
3. Commit your changes: `git commit -m 'Add feature'`
4. Push to the branch: `git push origin feature-name`
5. Submit a pull request.
