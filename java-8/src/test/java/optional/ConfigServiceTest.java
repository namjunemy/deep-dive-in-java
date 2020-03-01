package optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Optional 응용 테스트")
class ConfigServiceTest {

    Properties properties;
    ConfigService configService;

    @BeforeEach
    void setup() {
        properties = new Properties();
        properties.setProperty("a", "5");
        properties.setProperty("b", "true");
        properties.setProperty("c", "-2");
        configService = new ConfigService();
    }

    @Test
    @DisplayName("Non Optional readProperties 테스트")
    void readProperties() {
        assertEquals(5, configService.readDuration(properties, "a"));
        assertEquals(0, configService.readDuration(properties, "b"));
        assertEquals(0, configService.readDuration(properties, "c"));
        assertEquals(0, configService.readDuration(properties, "d"));
    }

    @Test
    @DisplayName("Optional readProperties 테스트")
    void readPropertiesOptional() {
        assertEquals(5, configService.readDurationOptional(properties, "a"));
        assertEquals(0, configService.readDurationOptional(properties, "b"));
        assertEquals(0, configService.readDurationOptional(properties, "c"));
        assertEquals(0, configService.readDurationOptional(properties, "d"));
    }
}