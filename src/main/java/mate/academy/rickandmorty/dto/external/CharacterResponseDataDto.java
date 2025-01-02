package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public record CharacterResponseDataDto(
        @JsonProperty("info")
        @NotNull(message = "Invalid info. Info shouldn't be null")
        CharacterInfoDataDto characterInfo,

        @NotEmpty(message = "Invalid results. Results shouldn't be empty or null.")
        List<CharacterResponseDto> results
) {
}
