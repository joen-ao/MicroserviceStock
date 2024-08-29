package bootcampragma.emazon.aplication.handler;

import bootcampragma.emazon.aplication.dto.CategoryRequest;
import bootcampragma.emazon.aplication.dto.CategoryResponse;
import bootcampragma.emazon.aplication.mapper.CategoryRequestMapper;
import bootcampragma.emazon.aplication.mapper.CategoryResponseMapper;
import bootcampragma.emazon.domain.api.ICategoryServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler {

    private final ICategoryServicePort categoryServicePort;
    private final CategoryRequestMapper categoryRequestMapper;
    private final CategoryResponseMapper categoryResponseMapper;

    @Override
    public void saveCategory(CategoryRequest categoryRequest) {
        categoryServicePort.saveCategory(categoryRequestMapper.toRequest(categoryRequest));
    }
    @Override
    public List<CategoryResponse> getAllCategory(Integer page, Integer size, String sortDirection) {
        return categoryResponseMapper.toResponseList(categoryServicePort.getAllCategory(page, size, sortDirection));
    }

}
