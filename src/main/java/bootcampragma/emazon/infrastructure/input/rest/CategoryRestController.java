package bootcampragma.emazon.infrastructure.input.rest;

import bootcampragma.emazon.aplication.dto.CategoryRequest;
import bootcampragma.emazon.aplication.dto.CategoryResponse;
import bootcampragma.emazon.aplication.handler.ICategoryHandler;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<CategoryResponse>> getAllCategory(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam(required = false) String sortDirection) {
        try {
            List<CategoryResponse> categories = categoryHandler.getAllCategory(page, size, sortDirection);
            if (categories.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.ok(categories);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

