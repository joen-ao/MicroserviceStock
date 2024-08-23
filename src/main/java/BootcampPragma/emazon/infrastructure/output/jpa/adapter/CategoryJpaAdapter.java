package BootcampPragma.emazon.infrastructure.output.jpa.adapter;

import BootcampPragma.emazon.domain.entity.Category;
import BootcampPragma.emazon.domain.spi.ICategoryPersistencePort;
import BootcampPragma.emazon.infrastructure.exception.CategoryAlreadyExistException;
import BootcampPragma.emazon.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import BootcampPragma.emazon.infrastructure.output.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;


    @Override
    public void saveCategory(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new CategoryAlreadyExistException();
        }
        categoryRepository.save(categoryEntityMapper.toCategoryEntity(category));
    }
}
