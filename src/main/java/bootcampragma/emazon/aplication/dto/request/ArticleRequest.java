package bootcampragma.emazon.aplication.dto.request;

import bootcampragma.emazon.aplication.util.AplicationConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ArticleRequest {
    private Long id;

    @NotNull(message = AplicationConstants.NAME_IS_REQUIRED)
    @NotBlank(message = AplicationConstants.NAME_CANNOT_BE_BLANK)
    private String name;

    @NotNull(message = AplicationConstants.DESCRIPTION_IS_REQUIRED)
    @NotBlank(message = AplicationConstants.DESCRIPTION_CANNOT_BE_BLANK)
    private String description;

    @NotNull(message = AplicationConstants.PRICE_IS_REQUIRED)
    @Positive(message = AplicationConstants.PRICE_MUST_BE_POSITIVE)
    private Double price;

    @NotNull(message = AplicationConstants.STOCK_IS_REQUIRED)
    @Positive(message = AplicationConstants.STOCK_MUST_BE_POSITIVE)
    private Integer stock;

    @NotNull(message = AplicationConstants.BRAND_IS_REQUIRED)
    private Long brandId;

    @NotNull(message = AplicationConstants.CATEGORIES_ARE_REQUIRED)
    private List<Long> categoriesId;

}
