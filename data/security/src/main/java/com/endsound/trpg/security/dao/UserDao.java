package com.endsound.trpg.security.dao;

import org.jooq.Configuration;

public class UserDao extends com.endsound.trpg.security.jooq.tables.daos.UserDao {
    public UserDao(Configuration configuration){
        super(configuration);
    }
}
