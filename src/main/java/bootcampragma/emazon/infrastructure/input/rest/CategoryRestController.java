package bootcampragma.emazon.infrastructure.input.rest;

import bootcampragma.emazon.aplication.dto.request.CategoryRequest;
import bootcampragma.emazon.aplication.dto.response.CategoryResponse;
import bootcampragma.emazon.aplication.handler.interfaces.ICategoryHandler;
import bootcampragma.emazon.domain.util.CustomPage;
import bootcampragma.emazon.domain.exception.category.InvalidSortDirectionException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

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

        try {
            if (!sortDirection.equalsIgnoreCase("asc") && !sortDirection.equalsIgnoreCase("desc")) {
                throw new InvalidSortDirectionException("Invalid sort direction");
            }

            CustomPage<CategoryResponse> customPage = categoryHandler.getAllCategory(page, size, sortDirection);

            if (customPage.getContent().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok(customPage);

        } catch (InvalidSortDirectionException e) {
            CustomPage<CategoryResponse> emptyPage = new CustomPage<>(Collections.emptyList(), 0, size, 0L, 0);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(emptyPage);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
