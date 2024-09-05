package bootcampragma.emazon.aplication.util;

import bootcampragma.emazon.domain.exception.category.InvalidSortDirectionException;


public class ArticlesValidation {
    public static final String SORT_BY_NAME = "name";
    public static final String SORT_BY_BRAND = "brand";
    public static final String SORT_BY_CATEGORIES_NAME =  "categories";
    public static final String ASCENDANT_SORT = "asc";
    public static final String DESCENDANT_SORT = "desc";

    private ArticlesValidation() {
    }

    public static void validationGetAllArticles(Integer page, Integer size, String sortDirection, String sortBy) {
        if (page == null || page < 0) {
            throw new IllegalArgumentException("Page number cannot be null or negative");
        }
        if (size == null || size < 0) {
            throw new IllegalArgumentException("Size number cannot be null or negative");
        }
        if (sortDirection == null || sortDirection.isEmpty()) {
            throw new IllegalArgumentException("Sort direction cannot be null or empty");
        }
        if (!sortDirection.equalsIgnoreCase(ASCENDANT_SORT) && !sortDirection.equalsIgnoreCase(DESCENDANT_SORT)) {
            throw new InvalidSortDirectionException("Invalid sort direction");
        }
        if (sortBy == null || sortBy.isEmpty()) {
            throw new IllegalArgumentException("Sort by cannot be null or empty");
        }
        if (!sortBy.equals(SORT_BY_NAME) && !sortBy.equals(SORT_BY_BRAND) && !sortBy.equals(SORT_BY_CATEGORIES_NAME)) {
            throw new IllegalArgumentException("Invalid sort by value");
        }

    }
}
