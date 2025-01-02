package mate.academy.rickandmorty.service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.external.CharacterSearchParameters;
import mate.academy.rickandmorty.dto.internal.CharacterInternalDto;
import mate.academy.rickandmorty.exception.EntityNotFoundException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import mate.academy.rickandmorty.repository.NameSpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final CharacterClient characterClient;
    private final NameSpecificationProvider specificationProvider;

    @PostConstruct
    private void fetchedAllDataFromApi() {
        List<CharacterResponseDto> charactersDto = characterClient.getCharacters();
        List<Character> characters = charactersDto.stream().map(characterMapper::toModel).toList();
        characterRepository.saveAll(characters);
    }

    public CharacterInternalDto getRandomCharacter() {
        long randomId = getRandomNumber();
        Character character = characterRepository.findById(randomId).orElseThrow(() ->
                new EntityNotFoundException("Can't find the Character by random ID"));
        return characterMapper.toDto(character);
    }

    public List<CharacterInternalDto> search(CharacterSearchParameters params) {
        System.out.println(params.name());
        Specification<Character> specification = specificationProvider.getSpecification(params);
        System.out.println(specification);
        List<Character> characters = characterRepository.findAll(specification);
        return characters.stream().map(characterMapper::toDto).toList();
    }

    private long getRandomNumber() {
        long count = characterRepository.count();
        if (count > 0) {
            return (long) (Math.random() * count);
        }
        throw new RuntimeException("Repository is empty");
    }
}
