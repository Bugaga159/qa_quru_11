package ru.example.hw17.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/api.properties"
})
public interface ProjectConfig extends Config {
    @Key("baseUrl")
    @DefaultValue("https://reqres.in/")
    String baseUrl();
}
