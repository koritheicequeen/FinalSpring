package character.storage.controller.model;

import java.util.HashSet;
import java.util.Set;

import character.storage.entity.Characters;
import character.storage.entity.Traits;
import character.storage.entity.Trauma;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TraumaData {
	
	private Long traumaID;
	private String traumaDescription;
	private int trustModifier;
	private int confidenceModifier;
	private int kindnessModifier;

    private Characters character;
	    private Set<String> traits = new HashSet<>();   
	
public TraumaData(Trauma trauma) {
	traumaID = trauma.getTraumaID();
	traumaDescription = trauma.getTraumaDescription();
	trustModifier = trauma.getTrustModifier();
	confidenceModifier = trauma.getConfidenceModifier();
	kindnessModifier = trauma.getKindnessModifier();
	character = new Characters(trauma.getCharacter());
	for (Traits trait : trauma.getTraits()) {
		traits.add(trait.getTraits());
	}
		}
}
