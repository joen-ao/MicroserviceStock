package bootcampragma.emazon.infrastructure.input.rest;

import bootcampragma.emazon.aplication.dto.request.CategoryRequest;
import bootcampragma.emazon.aplication.dto.response.CategoryResponse;
import bootcampragma.emazon.aplication.handler.interfaces.ICategoryHandler;
import bootcampragma.emazon.domain.util.CustomPage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryRestController {

    private final ICategoryHandler categoryHandler;

    @Operation(summary = "Save a new category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Category created",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<Void> saveCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        categoryHandler.saveCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/all")
    public ResponseEntity<CustomPage<CategoryResponse>> getAllCategory(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam(required = false, defaultValue = "asc") String sortDirection) {

        return ResponseEntity.ok(categoryHandler.getAllCategory(page, size, sortDirection));
    }
}
