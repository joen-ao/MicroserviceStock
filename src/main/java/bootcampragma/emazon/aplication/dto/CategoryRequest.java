package bootcampragma.emazon.aplication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {
    private Long id;

    @NotNull(message="Por favor diligenciar un nombre")
    @NotBlank(message = "Por favor, diligencie un nombre.")
    @Size(max = 50, message = "El nombre no puede exceder los 50 caracteres.")
    private String name;

    @NotNull(message="Por favor diligenciar una descripcion")
    @NotBlank(message = "Por favor, diligencie una descripción.")
    @Size(max = 90, message = "La descripción no puede exceder los 90 caracteres.")
    private String description;

}
