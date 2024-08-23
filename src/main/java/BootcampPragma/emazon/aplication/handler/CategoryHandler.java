package BootcampPragma.emazon.aplication.handler;

import BootcampPragma.emazon.aplication.dto.CategoryRequest;
import BootcampPragma.emazon.aplication.mapper.CategoryRequestMapper;
import BootcampPragma.emazon.domain.api.ICategoryServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler {

    private final ICategoryServicePort categoryServicePort;
    private final CategoryRequestMapper categoryRequestMapper;

    @Override
    public void saveCategory(CategoryRequest categoryRequest) {
        categoryServicePort.saveCategory(categoryRequestMapper.toRequest(categoryRequest));
    }

}
