package bootcampragma.emazon.aplication.dto.request;

import bootcampragma.emazon.aplication.util.AplicationConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {
    private Long id;

    @Size(min = 3, max = 50, message =AplicationConstants.NAME_MAX_MIN)
    @NotNull(message = AplicationConstants.NAME_IS_REQUIRED)
    @NotBlank(message = AplicationConstants.NAME_IS_REQUIRED)
    private String name;

    @Size(min = 3, max = 90, message = AplicationConstants.DESCRIPTION_MAX_MIN)
    @NotNull(message = AplicationConstants.DESCRIPTION_IS_REQUIRED)
    @NotBlank(message =AplicationConstants.DESCRIPTION_IS_REQUIRED)
    private String description;
}