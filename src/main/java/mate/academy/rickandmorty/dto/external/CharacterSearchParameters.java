package mate.academy.rickandmorty.dto.external;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CharacterSearchParameters(
        @Size(min = 1, max = 20, message = "Invalid name. Size should be between 1 to 20.")
        @NotEmpty(message = "Please enter a name.")
        String name) {
}
