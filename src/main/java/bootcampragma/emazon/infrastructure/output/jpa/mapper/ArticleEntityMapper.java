package bootcampragma.emazon.infrastructure.output.jpa.mapper;

import bootcampragma.emazon.domain.entity.Article;
import bootcampragma.emazon.infrastructure.output.jpa.entity.ArticleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {BrandEntityMapper.class, CategoryEntityMapper.class})
public interface ArticleEntityMapper {
    @Mapping(source = "brandId", target = "brand", qualifiedByName = "toBrandEntity")  // Nombre corregido
    @Mapping(source = "categoriesId", target = "categories", qualifiedByName = "toCategoryEntity")  // Nombre corregido
    ArticleEntity toArticleEntity(Article article);

    @Mapping(source = "brand.id", target = "brandId")
    Article toArticle(ArticleEntity articleEntity);
}
