package bootcampragma.emazon.aplication.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long stock;
    private BrandArticleResponse brand;
    private List<CategoryArticleResponse> categories;

}
