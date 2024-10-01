package character.storage.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import character.storage.entity.Trauma;

public interface TraumaDao extends JpaRepository<Trauma, Long>{

}
