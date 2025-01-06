package ru.itis.services.impl;

import ru.itis.models.Character;
import ru.itis.repositories.interfaces.CharacterRepository;
import ru.itis.services.interfaces.CharacterService;

import java.util.List;

public class CharacterServiceImpl implements CharacterService {

    private final CharacterRepository characterRepository;

    public CharacterServiceImpl(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public void addCharacter(ru.itis.models.Character character) {
        characterRepository.save(character);
    }

    @Override
    public List<Character> getCharacters() {
        return characterRepository.findAll();
    }

    @Override
    public int getCharId(String characterName) {
        return characterRepository.getCharIdByName(characterName);
    }
}
