package com.endsound.trpg.frontend.user;

import org.jooq.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("frontend-user-dao")
public class UserDao extends com.endsound.trpg.frontend.user.jooq.tables.daos.UserDao {
    @Autowired
    public UserDao(Configuration configuration){
        super(configuration);
    }
}
