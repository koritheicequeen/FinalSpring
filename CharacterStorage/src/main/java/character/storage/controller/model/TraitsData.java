package character.storage.controller.model;

import java.util.HashSet;
import java.util.Set;

import character.storage.entity.Characters;
import character.storage.entity.Trauma;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TraitsData {
    private String traits;
    private int trustModifier;
    private int confidenceModifier;
    private int kindnessModifier;
    private Set<Characters> character = new HashSet<>();
    private Set<Trauma> traumas = new HashSet<>();
    

}
