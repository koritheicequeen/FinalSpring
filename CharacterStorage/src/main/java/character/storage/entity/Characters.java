package character.storage.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Data
@NoArgsConstructor
public class Characters {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(unique = true)
	private Long characterID;
	 @Column(unique = true)
	private String characterName;
	private String characterWorld;
	private String characterAlignment;
	private int trust = 100;
	private int confidence = 20;
	private int kindness = 50;
	
	@OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Trauma> traumas = new HashSet<>(); 
	 @EqualsAndHashCode.Exclude
	    @ToString.Exclude
	    @ManyToMany(cascade = CascadeType.PERSIST)
	    @JoinTable(name = "character_trait", joinColumns = @JoinColumn(name = "characterID"), inverseJoinColumns = @JoinColumn(name = "traits"))
	private Set<Traits> traits = new HashSet<>();   
	
	 public Characters(Characters character) {
		 this.characterID = character.getCharacterID();
		this.characterName = character.getCharacterName();
		this.characterWorld = character.getCharacterWorld();
			this.characterAlignment = character.getCharacterAlignment();
			this.trust = character.getTrust();
			this.confidence = character.getConfidence();
			this.kindness = character.getKindness();
		}

}