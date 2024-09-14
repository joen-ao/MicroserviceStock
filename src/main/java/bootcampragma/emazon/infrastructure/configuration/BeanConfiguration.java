package bootcampragma.emazon.infrastructure.configuration;

import bootcampragma.emazon.domain.api.IArticleServicePort;
import bootcampragma.emazon.domain.api.IBrandServicePort;
import bootcampragma.emazon.domain.api.ICategoryServicePort;
import bootcampragma.emazon.domain.spi.IArticlePersistencePort;
import bootcampragma.emazon.domain.spi.IBrandPersistencePort;
import bootcampragma.emazon.domain.spi.ICategoryPersistencePort;
import bootcampragma.emazon.domain.usecase.ArticleUseCase;
import bootcampragma.emazon.domain.usecase.BrandUseCase;
import bootcampragma.emazon.domain.usecase.CategoryUseCase;
import bootcampragma.emazon.infrastructure.output.jpa.adapter.ArticleJpaAdapter;
import bootcampragma.emazon.infrastructure.output.jpa.adapter.BrandJpaAdapter;
import bootcampragma.emazon.infrastructure.output.jpa.adapter.CategoryJpaAdapter;
import bootcampragma.emazon.infrastructure.output.jpa.mapper.ArticleEntityMapper;
import bootcampragma.emazon.infrastructure.output.jpa.mapper.BrandEntityMapper;
import bootcampragma.emazon.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import bootcampragma.emazon.infrastructure.output.jpa.repository.IArticleRepository;
import bootcampragma.emazon.infrastructure.output.jpa.repository.IBrandRepository;
import bootcampragma.emazon.infrastructure.output.jpa.repository.ICategoryRepository;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;
    private final IBrandRepository brandRepository;
    private final BrandEntityMapper brandEntityMapper;
    private final IArticleRepository articleRepository;
    private final ArticleEntityMapper articleEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new CategoryUseCase(categoryPersistencePort(), categoryRepository);
    }

    @Bean
    public IBrandPersistencePort brandPersistencePort(){
        return new BrandJpaAdapter(brandRepository, brandEntityMapper);
    }

    @Bean
    public IBrandServicePort brandServicePort(){
        return new BrandUseCase(brandPersistencePort());
    }

    @Bean
    public IArticlePersistencePort articlePersistencePort() {
        return new ArticleJpaAdapter(articleRepository, articleEntityMapper, brandRepository, categoryRepository);
    }

    @Bean
    public IArticleServicePort articleServicePort(){
        return new ArticleUseCase(articlePersistencePort(), categoryPersistencePort(), brandPersistencePort());
    }


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Emazon API")
                        .version("1.0")
                        .description("API documentation for Emazon application"));
    }
}
