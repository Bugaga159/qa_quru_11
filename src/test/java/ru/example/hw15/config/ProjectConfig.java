package ru.example.hw15.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/${envTest}.properties"
})
public interface ProjectConfig extends Config {
    String browser();
    String browserVersion();
    String browserSize();
    String remoteDriverUrl();
}
