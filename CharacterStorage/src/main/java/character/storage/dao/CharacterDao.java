package character.storage.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import character.storage.entity.Characters;

public interface CharacterDao  extends JpaRepository<Characters, Long>{

}
