package bootcampragma.emazon.infrastructure.configuration;

import bootcampragma.emazon.aplication.handler.ICategoryHandler;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import static org.mockito.Mockito.mock;

@TestConfiguration
public class TestConfig {

    @Bean
    @Primary
    public ICategoryHandler categoryHandler() {
        return mock(ICategoryHandler.class);
    }
}