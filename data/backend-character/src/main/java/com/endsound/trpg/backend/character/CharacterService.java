package com.endsound.trpg.backend.character;

import com.endsound.trpg.backend.character.jooq.tables.pojos.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {
    @Autowired
    private CharacterDao characterDao;

    public Character insert(Character character){
        return characterDao.insertReturn(character);
    }

    public Character update(Character character){
        characterDao.update(character);
        return character;
    }

    public Character delete(Character character){
        characterDao.delete(character);
        return character;
    }

    public List<Character> fetch() {
        return characterDao.findAll();
    }
}
