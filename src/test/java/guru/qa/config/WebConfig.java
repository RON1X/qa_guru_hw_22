package guru.qa.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${environment}.properties"
})

public interface WebConfig extends Config {

    @Key("baseUrl")
    String getBaseUrl();

    @Key("baseUri")
    String getBaseUri();

    @Key("browserSize")
    @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("remoteUrl")
    String getRemoteUrl();
}