package bootcampragma.emazon.aplication.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;

}
