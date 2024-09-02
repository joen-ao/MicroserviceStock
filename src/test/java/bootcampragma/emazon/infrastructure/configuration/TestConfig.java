package bootcampragma.emazon.infrastructure.configuration;

import bootcampragma.emazon.aplication.handler.interfaces.IBrandHandler;
import bootcampragma.emazon.aplication.handler.interfaces.ICategoryHandler;
import bootcampragma.emazon.aplication.handler.interfaces.IArticleHandler;
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

    @Bean
    @Primary
    public IBrandHandler brandHandler() {
        return mock(IBrandHandler.class);
    }

    @Bean
    @Primary
    public IArticleHandler articleHandler() {
        return mock(IArticleHandler.class);
    }
}