package bootcampragma.emazon.domain.usecase;

import bootcampragma.emazon.domain.api.ICategoryServicePort;
import bootcampragma.emazon.domain.entity.Category;
import bootcampragma.emazon.domain.spi.ICategoryPersistencePort;
import bootcampragma.emazon.domain.util.CustomPage;
import bootcampragma.emazon.domain.exception.category.CategoryAlreadyExistException;
import bootcampragma.emazon.infrastructure.output.jpa.repository.ICategoryRepository;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;
    private final ICategoryRepository categoryRepository;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort, ICategoryRepository categoryRepository) {
        this.categoryPersistencePort = categoryPersistencePort;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void saveCategory(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw new CategoryAlreadyExistException();
        }

        categoryPersistencePort.saveCategory(category);
    }

    @Override
    public CustomPage<Category> getAllCategory(Integer page, Integer size, String sortDirection) {

        return categoryPersistencePort.getAllCategory(page, size, sortDirection);
    }
}
