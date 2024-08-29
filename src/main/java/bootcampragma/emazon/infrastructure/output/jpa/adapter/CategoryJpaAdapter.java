package bootcampragma.emazon.infrastructure.output.jpa.adapter;

import bootcampragma.emazon.domain.entity.Category;
import bootcampragma.emazon.domain.spi.ICategoryPersistencePort;
import bootcampragma.emazon.infrastructure.exception.*;
import bootcampragma.emazon.infrastructure.output.jpa.entity.CategoryEntity;
import bootcampragma.emazon.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import bootcampragma.emazon.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

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
        if(category.getName().isEmpty()){
            throw new CategoryNameEmptyException();
        }
        if(category.getDescription().isEmpty()){
            throw new CategoryDescriptionEmptyException();
        }
        if(category.getName().length() > Category.MAX_NAME_LENGTH){
            throw new CategoryOversizeNameException();
        }
        if(category.getDescription().length() > Category.MAX_DESCRIPTION_LENGTH){
            throw new CategoryOversizeDescriptionException();
        }
        categoryRepository.save(categoryEntityMapper.toCategoryEntity(category));
    }


    @Override
    public List<Category> getAllCategory(Integer page, Integer size, String sortDirection) {
        Pageable pagination;
        if (sortDirection == null || sortDirection.isEmpty()) {
            pagination = PageRequest.of(page, size);
        } else {
            pagination = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), "name"));
        }
        List<CategoryEntity> categories = categoryRepository.findAll(pagination).getContent();
        return categoryEntityMapper.toCategoryList(categories);
    }
}

