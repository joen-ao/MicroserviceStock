package bootcampragma.emazon.aplication.dto.response;

import lombok.Data;

@Data
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;

    public CategoryResponse(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public CategoryResponse() {
    }
}
