package bootcampragma.emazon.aplication.dto.response;

import lombok.Data;

@Data
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;


    public CategoryResponse() {
        //
    }

    public void filterFields() {
        this.description = null;
    }
}
