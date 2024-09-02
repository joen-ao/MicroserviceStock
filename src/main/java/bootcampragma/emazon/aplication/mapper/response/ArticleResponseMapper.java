package bootcampragma.emazon.aplication.mapper.response;

import bootcampragma.emazon.aplication.dto.response.ArticleResponse;
import bootcampragma.emazon.domain.entity.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleResponseMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "stock", target = "stock")
    @Mapping(source = "brand", target = "brand", qualifiedByName = "toResponse")
    @Mapping(source = "category", target = "category", qualifiedByName = "toResponse")
    List<ArticleResponse> toResponseList(List<Article> articleRequestList);
}
