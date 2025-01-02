package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterSearchParameters;
import mate.academy.rickandmorty.dto.internal.CharacterInternalDto;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Tag(name = "Character management", description = "Endpoints of managing characters")
@RestController
@RequestMapping("api/character")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    @GetMapping
    @Operation(summary = "Get Character by random id", description = "The request randomly "
            + "generates a wiki about one character.")
    CharacterInternalDto getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @GetMapping("/search")
    @Operation(summary = "Search characters", description = "Returns a list of all characters "
            + "whose name contains the search string")
    List<CharacterInternalDto> search(@Valid CharacterSearchParameters searchParameters) {
        List<CharacterInternalDto> searchedCharacters = characterService.search(searchParameters);
        if (searchedCharacters.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No characters found");
        }
        return searchedCharacters;
    }
}
