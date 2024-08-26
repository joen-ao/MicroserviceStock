package bootcampragma.emazon.domain.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {
    public static final int MAX_NAME_LENGTH = 50;
    public static final int MAX_DESCRIPTION_LENGTH = 90;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(message = "La descripción de la categoría es obligatoria")
    @Size(max = MAX_NAME_LENGTH, message = "El nombre de la categoría no puede exceder los 50 caracteres")
    private String name;

    @NotBlank(message = "La descripción de la categoría es obligatoria")
    @Size(max = MAX_DESCRIPTION_LENGTH, message = "La descripción de la categoría no puede exceder los 90 caracteres")
    private String description;

}
