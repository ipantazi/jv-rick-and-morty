package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDataDto;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character/";
    private final ObjectMapper objectMapper;

    public List<CharacterResponseDto> getCharacters() {
        List<CharacterResponseDto> charactersResponse = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();

        for (String url = BASE_URL; url != null; ) {
            HttpResponse<String> response;
            try {
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .uri(URI.create(url))
                        .build();
                response = httpClient.send(
                        httpRequest,
                        HttpResponse.BodyHandlers.ofString()
                );
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Failed to send HTTP request "
                        + "or receive response", e);
            }

            try {
                CharacterResponseDataDto dataDto = objectMapper.readValue(
                        response.body(),
                        CharacterResponseDataDto.class
                );
                charactersResponse.addAll(dataDto.results());
                url = dataDto.characterInfo().next();
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Failed to convert JSON response", e);
            }
        }
        return charactersResponse;
    }
}
