package bootcampragma.emazon.infrastructure.input.rest;

import bootcampragma.emazon.aplication.dto.request.BrandRequest;
import bootcampragma.emazon.aplication.dto.response.BrandResponse;
import bootcampragma.emazon.aplication.handler.interfaces.IBrandHandler;
import bootcampragma.emazon.domain.util.CustomPage;
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

    @Operation(summary = "Get all brands", description = "This endpoint returns a paginated list of all brands.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of brands"),
            @ApiResponse(responseCode = "400", description = "Invalid pagination parameters", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/all")
    public ResponseEntity<CustomPage<BrandResponse>> getAllBrand(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        CustomPage<BrandResponse> brandResponses = brandHandler.getAllBrand(page, size, sortDirection);
        if (brandResponses.getContent().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(brandResponses);
    }
}
