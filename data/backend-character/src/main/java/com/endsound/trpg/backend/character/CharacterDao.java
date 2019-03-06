package com.endsound.trpg.backend.character;

import com.endsound.trpg.backend.character.jooq.Tables;
import com.endsound.trpg.backend.character.jooq.tables.pojos.Character;
import com.endsound.trpg.backend.character.jooq.tables.records.CharacterRecord;
import org.jooq.Configuration;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CharacterDao extends com.endsound.trpg.backend.character.jooq.tables.daos.CharacterDao {
    @Autowired
    public CharacterDao(Configuration configuration){
        super(configuration);
    }

    public Character insertReturn(Character pojo){
        CharacterRecord record = DSL.using(configuration()).newRecord(Tables.CHARACTER, pojo);
        record.insert();
        return mapper().map(record);
    }
}
