package character.storage.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import character.storage.entity.Traits;

public interface TraitsDao extends JpaRepository<Traits, String>{

}
