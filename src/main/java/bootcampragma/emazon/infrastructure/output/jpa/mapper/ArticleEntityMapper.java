package bootcampragma.emazon.infrastructure.output.jpa.mapper;

import bootcampragma.emazon.domain.entity.Article;
import bootcampragma.emazon.domain.util.CustomArticlePage;
import bootcampragma.emazon.infrastructure.output.jpa.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BrandEntityMapper.class, CategoryEntityMapper.class})
public interface ArticleEntityMapper {

    @Mapping(source = "brand", target = "brandEntity")
    @Mapping(source = "categories", target = "categoriesEntity")
    ArticleEntity toArticleEntity(Article article);

    @Mapping(source = "brandEntity", target = "brand")
    @Mapping(source = "categoriesEntity", target = "categories")
    Article toArticle(ArticleEntity articleEntity);

    List<Article> toArticlesList(List<ArticleEntity> articleEntities);

    default CustomArticlePage<Article> toCustomPageArticle(Page<ArticleEntity> page) {
        CustomArticlePage<Article> pageCustom = new CustomArticlePage<>();

        List<Article> content = page.getContent().stream()
                .map(this::toArticle)
                .toList();


        pageCustom.setContent(content);
        pageCustom.setTotalElements(page.getTotalElements());
        pageCustom.setTotalPages(page.getTotalPages());
        pageCustom.setPageSize(page.getSize());
        pageCustom.setPageNumber(page.getNumber());
        return pageCustom;
    }
}
