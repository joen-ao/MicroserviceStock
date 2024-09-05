package bootcampragma.emazon.infrastructure.input.rest;

import bootcampragma.emazon.aplication.dto.request.ArticleRequest;
import bootcampragma.emazon.aplication.dto.response.ArticleResponse;
import bootcampragma.emazon.aplication.handler.interfaces.IArticleHandler;
import bootcampragma.emazon.domain.util.CustomArticlePage;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleRestController {

    private final IArticleHandler articleHandler;


    @PostMapping
    public ResponseEntity<Void> saveArticle(
            @Valid @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Article data to be created", required = true,
                    content = @Content(schema = @Schema(implementation = ArticleRequest.class)))
            ArticleRequest articleRequest) {
        articleHandler.saveArticle(articleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/all")
    public ResponseEntity<CustomArticlePage<ArticleResponse>> getAllArticles(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "ASC") String sortDirection,
            @RequestParam(defaultValue = "name") String sortBy) {

        CustomArticlePage<ArticleResponse> articleResponses = articleHandler.getAllArticle(page, size, sortDirection, sortBy);
        return ResponseEntity.ok(articleResponses);
    }

}
