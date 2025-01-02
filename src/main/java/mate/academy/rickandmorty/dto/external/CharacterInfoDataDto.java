package mate.academy.rickandmorty.dto.external;

import org.hibernate.validator.constraints.URL;

public record CharacterInfoDataDto(
        @URL(message = "Invalid next. Next should be url.")
        String next,

        @URL(message = "Invalid next. Next should be url.")
        String prev
) {
}
