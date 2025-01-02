package mate.academy.rickandmorty.repository;

import mate.academy.rickandmorty.dto.external.CharacterSearchParameters;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class NameSpecificationProvider {
    public Specification<Character> getSpecification(CharacterSearchParameters params) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(
                                root.get("name")),
                        "%" + params.name().toLowerCase() + "%"));
    }
}
