package bootcampragma.emazon.infrastructure.output.jpa.mapper;

import bootcampragma.emazon.domain.entity.Article;
import bootcampragma.emazon.infrastructure.output.jpa.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BrandEntityMapper.class, CategoryEntityMapper.class})
public interface ArticleEntityMapper {
    @Mapping(source = "brandId", target = "brand", qualifiedByName = "toBrandEntity")
    @Mapping(source = "categoriesId", target = "categories", qualifiedByName = "toCategoryEntity")
    ArticleEntity toArticleEntity(Article article);

    @Mapping(source = "brand.id", target = "brandId")
    @Mapping(source = "categories", target = "categoriesId", qualifiedByName = "toCategoryEntityListToIdList")
    Article toArticle(ArticleEntity articleEntity);

    List<Article> toArticleList(List<ArticleEntity> articleEntities);
}

