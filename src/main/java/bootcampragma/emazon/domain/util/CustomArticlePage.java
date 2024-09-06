package bootcampragma.emazon.domain.util;
import java.util.List;

public class CustomArticlePage<T> extends CustomPage<T>{
    private final String sortBy;

    public CustomArticlePage(List<T> content, int pageNumber, int pageSize, long totalElements, int totalPages, String sortBy) {
        super(content, pageNumber, pageSize, totalElements, totalPages);
        this.sortBy = sortBy;
    }

    public String getSortBy() {
        return sortBy;
    }

}
