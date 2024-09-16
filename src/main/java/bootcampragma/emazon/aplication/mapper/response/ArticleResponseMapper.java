package bootcampragma.emazon.aplication.mapper.response;

import bootcampragma.emazon.aplication.dto.response.ArticleResponse;
import bootcampragma.emazon.domain.entity.Article;
import bootcampragma.emazon.domain.util.CustomPage;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring", uses = {BrandResponseMapper.class, CategoryResponseMapper.class})
public interface ArticleResponseMapper {

    ArticleResponse toResponse(Article article);

    default CustomPage<ArticleResponse> toResponseList(CustomPage<Article> articleRequestPage) {
        CustomPage<ArticleResponse> pageCustom = new CustomPage<>();

        List<ArticleResponse> content = articleRequestPage.getContent().stream()
                .map(this::toResponse)
                .toList();

        pageCustom.setContent(content);
        pageCustom.setTotalPages(articleRequestPage.getTotalPages());
        pageCustom.setTotalElements(articleRequestPage.getTotalElements());
        pageCustom.setPageNumber(articleRequestPage.getPageNumber());
        pageCustom.setPageSize(articleRequestPage.getPageSize());
        return pageCustom;
    }

}
