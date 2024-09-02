package bootcampragma.emazon.aplication.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArticleResponse {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private Long brandId;
    private List<Long> categoriesId;
}
