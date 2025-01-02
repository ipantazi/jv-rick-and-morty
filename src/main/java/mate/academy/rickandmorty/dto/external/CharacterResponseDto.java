package mate.academy.rickandmorty.dto.external;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CharacterResponseDto(
        @Positive(message = "Invalid id. Id should be positive.")
        Long id,

        @NotBlank(message = "Invalid name. Name should not be blank")
        @Size(min = 3, max = 20, message = "Invalid name. Name should be between 3 to 20")
        String name,

        @Pattern(regexp = "^(Alive|Dead|unknown)$", message = "Invalid status. "
                + "The status of the character must be one of: 'Alive', 'Dead' or 'unknown'.")
        String status,

        @Pattern(regexp = "^(Female|Male|Genderless|unknown)", message = "Invalid gender. "
                + "The gender of the character must be one of: 'Female', 'Male', 'Genderless' "
                + "or 'unknown'.")
        String gender
) {
}
