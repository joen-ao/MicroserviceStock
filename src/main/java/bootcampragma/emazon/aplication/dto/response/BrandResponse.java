package bootcampragma.emazon.aplication.dto.response;

import lombok.Data;

@Data
public class BrandResponse {
    private Long id;
    private String name;
    private String description;

    public void filterFields() {
        this.id = null;
        this.description = null;
    }
}
