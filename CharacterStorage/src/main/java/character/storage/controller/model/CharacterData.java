package character.storage.controller.model;

import java.util.HashSet;
import java.util.Set;

import character.storage.entity.Characters;
import character.storage.entity.Traits;
import character.storage.entity.Trauma;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CharacterData {
	@GeneratedValue
	 @Column(unique = true)
	private Long characterID;
	 @Column(unique = true)
	private String characterName;
	 
	private String characterWorld;
	private String characterAlignment;
	private int trust = 100;
	private int confidence = 20;
	private int kindness = 50;
    private Set<Trauma> traumas = new HashSet<>(); 
	private Set<Traits> traits = new HashSet<>();   
	public CharacterData(Characters characters) {
	this.characterID = characters.getCharacterID();
	this.characterName = characters.getCharacterName();
	this.characterName = characters.getCharacterWorld();
	this.characterAlignment = characters.getCharacterAlignment();
	int trustModifier = 0;
	int confidenceModifier = 0;
	int kindnessModifier = 0;
	for(Trauma trauma : traumas) {
		trustModifier += trauma.getTrustModifier();
		confidenceModifier += trauma.getConfidenceModifier();
		kindnessModifier += trauma.getKindnessModifier();
	}
	for(Traits traits : traits) {
		trustModifier += traits.getTrustModifier();
		confidenceModifier += traits.getConfidenceModifier();
		kindnessModifier += traits.getKindnessModifier();
	}
	this.trust = 100 + trustModifier;
	this.confidence = 20 + confidenceModifier;
	this.kindness = 50 + kindnessModifier;
	}
}
