package BootcampPragma.emazon.infrastructure.configuration;

import BootcampPragma.emazon.domain.api.ICategoryServicePort;
import BootcampPragma.emazon.domain.spi.ICategoryPersistencePort;
import BootcampPragma.emazon.domain.usecase.CategoryUseCase;
import BootcampPragma.emazon.infrastructure.output.jpa.adapter.CategoryJpaAdapter;
import BootcampPragma.emazon.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import BootcampPragma.emazon.infrastructure.output.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor

public class BeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort(){
        return new CategoryUseCase(categoryPersistencePort());
    }

}