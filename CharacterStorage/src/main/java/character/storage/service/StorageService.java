package character.storage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import character.storage.controller.model.CharacterData;
import character.storage.controller.model.TraumaData;
import character.storage.dao.CharacterDao;
import character.storage.dao.TraitsDao;
import character.storage.dao.TraumaDao;
import character.storage.entity.Characters;
import character.storage.entity.Traits;
import character.storage.entity.Trauma;


@Service
public class StorageService {
	@Autowired
	private TraumaDao traumaDao;
	@Autowired
	private TraitsDao traitsDao;
	@Transactional (readOnly = false)
	public TraumaData insertTrauma(TraumaData traumaData, String name) {
		Long traumaID = traumaData.getTraumaID();
		Trauma trauma;
		if (Objects.isNull(traumaID)) {
			trauma = new Trauma();
		}else 
		trauma = findOrCreateTrauma(traumaID);
		setFieldsInTrauma(trauma, traumaData);
		Long ID = null;
		 try {
	             ID = Long.parseLong(name);
	        } catch (NumberFormatException e) {
	        }
		 Characters character = findCharacterByNameOrID(ID, name);
		 Set<Trauma> traumas = character.getTraumas();
		 traumas.add(trauma);
		 character.setTraumas(traumas);
		return new TraumaData(traumaDao.save(trauma));
	}

	private void setFieldsInTrauma(Trauma trauma, TraumaData traumaData) {
		
		
	}

	private Trauma findOrCreateTrauma(Long traumaID) {
		Trauma trauma;
		if (Objects.isNull(traumaID)) {
			trauma = new Trauma();
		}
		else trauma = findTraumaByID(traumaID);
		return trauma;
	}

	public Trauma findTraumaByID(Long traumaID) {
		return traumaDao.findById(traumaID).orElseThrow(() -> new NoSuchElementException("Trauma with ID = " +traumaID+ " not found"));
	}
	@Autowired
	private CharacterDao characterDao;
	@Transactional (readOnly = false)
	public CharacterData insertcharacter(CharacterData characterData, String traits) {
		
		Long characterID = characterData.getCharacterID();
		Characters character = findOrCreatecharacter(characterID);
		
		setFieldsIncharacter(character, characterData, traits);
		if (traits != null) {
		 String[] parts = traits.split("/");
		 
		 for (String part : parts) {
			 Traits trait = traitsDao.findById(part).orElseThrow(() -> new NoSuchElementException(part+ " not found"));;
			 character.getTraits().add(trait);
			 characterData.getTraits().add(trait);}
		}
		for (Characters character2 : characterDao.findAll()) {
			if (character2.getCharacterName().equals(character.getCharacterName())) {
				return null;
			}
		}
		return new CharacterData(characterDao.save(character));
	}

	private void setFieldsIncharacter(Characters character, CharacterData characterData, String traits) {
		character.setCharacterName(characterData.getCharacterName());
		character.setCharacterAlignment(characterData.getCharacterAlignment());
		character.setCharacterWorld(characterData.getCharacterWorld());
	
		
		 
		
		
	}

	private Characters findOrCreatecharacter(Long characterID) {
		Characters character;
		if (Objects.isNull(characterID)) {
			character = new Characters();
		}
		else character = findcharacterByID(characterID);
		return character;
	}

	private Characters findcharacterByID(Long characterID) {
		return characterDao.findById(characterID).orElseThrow(() -> new NoSuchElementException("character with ID = " +characterID+ " not found"));
	}

	public List<TraumaData> retrieveAllTrauma(Long ID, String Name) {
		Characters character = findCharacterByNameOrID(ID, Name);
		List<TraumaData> traumaData = new ArrayList<>();
		for (Trauma trauma : character.getTraumas()) {
			TraumaData traumadata = new TraumaData(traumaDao.save(trauma));
			traumaData.add(traumadata);
		}
		return traumaData;
	}

	public Characters findCharacterByNameOrID(Long ID, String name) {
		Characters character = null;
		if (!Objects.isNull(ID)) {
			character = findcharacterByID(ID);	
		} else {
			List<Characters> characters = characterDao.findAll();
			for (Characters Char : characters) {
				if (Char.getCharacterName().equals(name)) {
					character = Char;
					break;
				}
			}
		}
		return character;
	}

	public CharacterData retrieveCharacterData(String name) {
		Long ID = null;
		 try {
             ID = Long.parseLong(name);
        } catch (NumberFormatException e) {
        }
		 Characters character = findCharacterByNameOrID(ID,name);
		return new CharacterData(characterDao.save(character));
	}

	public List<CharacterData> retrieveAllCharacters() {
		List<Characters> characters = characterDao.findAll();
		List<CharacterData> characterDatas = new ArrayList<>();
		for (Characters chars : characters) {
			characterDatas.add(new CharacterData(chars));
		}
		return characterDatas;
	}

	public TraumaData retrieveTrauma(Long iD) {
		Trauma trauma = findTraumaByID(iD);
		
		return new TraumaData(trauma);
	}

	public void deleteTrauma(Trauma trauma) {
		traumaDao.delete(trauma);
	}

	public void deleteCharacter(Long ID) {
		Characters character = findcharacterByID(ID);
		for (Trauma trauma : character.getTraumas()) {
			deleteTrauma(trauma);
		}
		characterDao.delete(character);
	}
	

}
