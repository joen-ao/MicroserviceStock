package bootcampragma.emazon.aplication.mapper.request;

import bootcampragma.emazon.aplication.dto.request.ArticleRequest;
import bootcampragma.emazon.domain.entity.Article;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ArticleRequestMapper {
    Article toRequest(ArticleRequest articleRequest);
}
