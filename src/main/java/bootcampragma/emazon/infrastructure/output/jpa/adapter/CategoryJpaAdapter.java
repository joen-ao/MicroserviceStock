package bootcampragma.emazon.infrastructure.output.jpa.adapter;

import bootcampragma.emazon.domain.entity.Category;
import bootcampragma.emazon.domain.spi.ICategoryPersistencePort;
import bootcampragma.emazon.domain.util.CustomPage;
import bootcampragma.emazon.infrastructure.output.jpa.entity.CategoryEntity;
import bootcampragma.emazon.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import bootcampragma.emazon.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(categoryEntityMapper.toCategoryEntity(category));
    }

    @Override
    public CustomPage<Category> getAllCategory(Integer page, Integer size, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), "name");

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<CategoryEntity> categoryEntityPage = categoryRepository.findAll(pageable);

        return categoryEntityMapper.toCustomPage(categoryEntityPage);
    }
    @Override
    public boolean existsById(Long id) {
        return categoryRepository.findById(id).isPresent();
    }

    @Override
    public boolean findByName(String name) {
        return categoryRepository.findByName(name).isPresent();
    }
}



