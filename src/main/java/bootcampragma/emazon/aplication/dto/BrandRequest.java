package bootcampragma.emazon.aplication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandRequest {

    private Long id;

    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    private String name;

    @Size(min = 3, max = 120, message = "Description must be between 3 and 120 characters")
    @NotNull(message = "Description is required")
    @NotBlank(message = "Description is required")
    private String description;
}
