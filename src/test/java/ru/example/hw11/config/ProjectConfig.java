package ru.example.hw11.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/local.properties"
})
public interface ProjectConfig extends Config {
    String browser();
    String browserVersion();
    String browserSize();
    String remoteDriverUrl();
}
