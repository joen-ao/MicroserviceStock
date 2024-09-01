package bootcampragma.emazon.infrastructure.input.rest;

import bootcampragma.emazon.aplication.dto.BrandRequest;
import bootcampragma.emazon.aplication.dto.BrandResponse;
import bootcampragma.emazon.aplication.handler.IBrandHandler;
import bootcampragma.emazon.domain.util.CustomPageBrand;
import bootcampragma.emazon.infrastructure.exception.category.InvalidSortDirectionException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
@Tag(name = "Brand", description = "Operations related to brand management")
public class BrandRestController {

    private final IBrandHandler brandHandler;

    @Operation(summary = "Create a new brand", description = "This endpoint allows creating a new brand by providing brand details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Brand created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Void> saveBrand(
            @Valid @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Brand data to be created", required = true,
                    content = @Content(schema = @Schema(implementation = BrandRequest.class)))
            BrandRequest brandRequest) {
        brandHandler.saveBrand(brandRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/all")
    public ResponseEntity<CustomPageBrand<BrandResponse>> getAllBrand(
            @RequestParam Integer page,
            @RequestParam Integer size,
            @RequestParam(required = false, defaultValue = "asc") String sortDirection) {

        try {
            if (!sortDirection.equalsIgnoreCase("asc") && !sortDirection.equalsIgnoreCase("desc")) {
                throw new InvalidSortDirectionException("Invalid sort direction");
            }

            CustomPageBrand<BrandResponse> customPage = brandHandler.getAllBrand(page, size, sortDirection);


            if (customPage.getContent().isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.ok(customPage);

        } catch (InvalidSortDirectionException e) {
            CustomPageBrand<BrandResponse> emptyPage = new CustomPageBrand<>(Collections.emptyList(), 0, size, 0L, 0);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(emptyPage);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
