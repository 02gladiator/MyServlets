package ru.itis.services.interfaces;

import ru.itis.models.Character;

import java.util.List;

public interface CharacterService {
    void addCharacter(Character character);
    List<Character> getCharacters();
    int getCharId(String characterName);
}
