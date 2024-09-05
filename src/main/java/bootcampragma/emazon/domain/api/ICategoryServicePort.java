package bootcampragma.emazon.domain.api;

import bootcampragma.emazon.domain.entity.Category;
import bootcampragma.emazon.domain.util.CustomPage;

import java.util.List;

public interface ICategoryServicePort {
    void saveCategory(Category category);
    CustomPage<Category> getAllCategory(Integer page, Integer size, String sortDirection);
    List<Category> findByArticleId(Long articleId);
}