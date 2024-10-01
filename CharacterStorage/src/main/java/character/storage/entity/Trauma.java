package character.storage.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Trauma {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long traumaID;
	private String traumaDescription;
	private int trustModifier = 0;
	private int confidenceModifier = 0;
	private int kindnessModifier = 0;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "characterID")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Characters character;
	
	 @EqualsAndHashCode.Exclude
	    @ToString.Exclude
	    @ManyToMany(cascade = CascadeType.PERSIST)
	    @JoinTable(name = "trauma_trait", joinColumns = @JoinColumn(name = "traumaID"), inverseJoinColumns = @JoinColumn(name = "traits"))
	    private Set<Traits> traits = new HashSet<>();   
	

}
