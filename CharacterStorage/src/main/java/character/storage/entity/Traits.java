package character.storage.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Traits {

    @Id
    @Column(unique = true)
    private String traits;
    private int trustModifier = 0;
    private int confidenceModifier = 0;
    private int kindnessModifier = 0;

    // Many-to-Many relationship with Character
    @ManyToMany(mappedBy = "traits", cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Characters> character = new HashSet<>();

    // Many-to-Many relationship with Trauma
    @ManyToMany(mappedBy = "traits", cascade = CascadeType.PERSIST)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Trauma> traumas = new HashSet<>();
	  
/*
Trust Issues
Abandonment Issues
Low Self-Esteem
Anxiety
Depression
Difficulty Regulating Emotions
Hyper-vigilance
Post-Traumatic Stress Symptoms
Attachment Issues
Overly Prideful
Manipulative Behavior
Fear of Intimacy
Aggression or Hostility
Social Withdrawal
Perfectionism
 */
}
