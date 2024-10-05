package character.storage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import character.storage.controller.model.TraumaData;
import character.storage.entity.Trauma;
import character.storage.controller.model.CharacterData;
import character.storage.controller.model.TraitsData;
import character.storage.service.StorageService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/character_controller")
@Slf4j
public class CharacterStorageController {
	@Autowired
	private StorageService storageService;

	@PostMapping("/trauma/{name}")
	public TraumaData insertTraumaData(@RequestBody TraumaData traumaData, @PathVariable String name) {
		log.info("Creating new Trauma");
		return storageService.insertTrauma(traumaData, name);
	}
	@PostMapping("/character/{Traits}")
	public CharacterData insertCharacterDataWithTraits(@RequestBody CharacterData characterData,   @PathVariable String Traits) {
		log.info("Creating new Character");
		return storageService.insertcharacter(characterData, Traits);
	}
	@PostMapping("/character")
	public CharacterData insertCharacterData(@RequestBody CharacterData characterData) {
		log.info("Creating new Character");
		return storageService.insertcharacter(characterData, null);
	}
	@GetMapping("/getAllTrauma/ID/{ID}")
	public List<TraumaData> retrieveAllTraumaDataByID(@PathVariable Long ID) {
		log.info("All traumas have been retrieved");
		return storageService.retrieveAllTrauma(ID, null);
	}
	@GetMapping("/getAllTrauma/Name/{name}")
	public List<TraumaData> retrieveAllTraumaDataByID(@PathVariable String name) {
		log.info("All traumas have been retrieved");
		return storageService.retrieveAllTrauma(null, name);
	}
	@GetMapping("/character/{name}")
	public CharacterData retrieveCharacterData(@PathVariable String name) {
		log.info("Retrieving character " + name);
		return storageService.retrieveCharacterData(name);
	}
	@GetMapping("/characters")
	public List<CharacterData> retrieveAllCharacterData() {
		log.info("Retrieving  all characters");
		return storageService.retrieveAllCharacters();
	}
	@GetMapping("/Trauma/{ID}")
	public TraumaData retrieveTraumaDataByID(@PathVariable Long ID) {
		log.info("All traumas have been retrieved");
		return storageService.retrieveTrauma(ID);
	}
	@GetMapping("/Trait/{name}")
	public TraitsData retrieveTraumaDataByID(@PathVariable String name) {
		log.info("All traumas have been retrieved");
		return storageService.retrieveTrait(name);
	}
	@PutMapping("/character/{name}/{traits}")
	public CharacterData updateCharacter(@PathVariable String name, @PathVariable String traits, @RequestBody CharacterData characterData) {
		log.info("Character: " + name + " has been updated");
		return storageService.insertcharacter(characterData, traits);
		
	}
	@PutMapping("/character/{name}")
	public CharacterData updateCharacter(@PathVariable String name, @RequestBody CharacterData characterData) {
		log.info("Character: " + name + " has been updated");
		return storageService.insertcharacter(characterData, null);
		
	}
	@PutMapping("/trauma/{name}")
	public TraumaData updateTraumaData(@RequestBody TraumaData traumaData, @PathVariable String name) {
		log.info("Editing Trauma");
		return storageService.insertTrauma(traumaData, name);
	}
		
	@DeleteMapping("/Trauma/{ID}")
	public Map<String, String> DeleteTrauma(@PathVariable Long ID){
		if (ID == null) {
			return Map.of("Message", "Please specify a Trauma ID");
		}
		log.info("Trauma with ID = " + ID + " has been deleted");
		Trauma trauma = storageService.findTraumaByID(ID);
		storageService.deleteTrauma(trauma);
		return Map.of("Message", "Trauma has been deleted");
	}
	@DeleteMapping("/Character/{ID}")
	public Map<String, String> DeleteCharacter(@PathVariable Long ID){
		if (ID == null) {
			return Map.of("Message", "Please specify a Character ID");
		}
		log.info("Character with ID = " + ID + " has been deleted");
		storageService.deleteCharacter(ID);
		return Map.of("Message", "Character has been deleted");
	}
	
	
}

