package com.endsound.trpg.backend.player;

import org.jooq.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("player-user-dao")
public class UserDao extends com.endsound.trpg.backend.player.jooq.tables.daos.UserDao {
    @Autowired
    public UserDao(Configuration configuration){
        super(configuration);
    }
}
