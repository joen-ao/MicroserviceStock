package bootcampragma.emazon.infrastructure.input.rest;

import bootcampragma.emazon.aplication.dto.request.ArticleRequest;
import bootcampragma.emazon.aplication.dto.response.ArticleResponse;
import bootcampragma.emazon.aplication.handler.interfaces.IArticleHandler;
import bootcampragma.emazon.domain.util.ArticlesValidation;
import bootcampragma.emazon.domain.util.CustomArticlePage;
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
@RequestMapping("/article")
@RequiredArgsConstructor
@Tag(name = "Article", description = "Operations related to article management")
public class ArticleRestController {

    private final IArticleHandler articleHandler;

    @Operation(summary = "Create a new article", description = "This endpoint allows creating a new article by providing article details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Article created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Void> saveArticle(
            @Valid @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Article data to be created", required = true,
                    content = @Content(schema = @Schema(implementation = ArticleRequest.class)))
            ArticleRequest articleRequest) {
        articleHandler.saveArticle(articleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get all articles", description = "This endpoint returns a paginated list of all articles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of articles"),
            @ApiResponse(responseCode = "400", description = "Invalid pagination parameters", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @GetMapping("/all")
    public ResponseEntity<CustomArticlePage<ArticleResponse>> getAllArticle(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "ASC") String sortDirection,
            @RequestParam(defaultValue = "name") String sortBy) {
        ArticlesValidation.validationGetAllArticles(page, size, sortDirection, sortBy);

        CustomArticlePage<ArticleResponse> articleResponses = articleHandler.getAllArticles(page, size, sortDirection, sortBy);
        return ResponseEntity.ok(articleResponses);
    }
}