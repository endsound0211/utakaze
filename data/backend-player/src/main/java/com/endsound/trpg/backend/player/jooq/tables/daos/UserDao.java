/*
 * This file is generated by jOOQ.
 */
package com.endsound.trpg.backend.player.jooq.tables.daos;


import com.endsound.trpg.backend.player.jooq.tables.User;
import com.endsound.trpg.backend.player.jooq.tables.records.UserRecord;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * 使用者資訊
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserDao extends DAOImpl<UserRecord, com.endsound.trpg.backend.player.jooq.tables.pojos.User, Integer> {

    /**
     * Create a new UserDao without any configuration
     */
    public UserDao() {
        super(User.USER, com.endsound.trpg.backend.player.jooq.tables.pojos.User.class);
    }

    /**
     * Create a new UserDao with an attached configuration
     */
    public UserDao(Configuration configuration) {
        super(User.USER, com.endsound.trpg.backend.player.jooq.tables.pojos.User.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(com.endsound.trpg.backend.player.jooq.tables.pojos.User object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.endsound.trpg.backend.player.jooq.tables.pojos.User> fetchById(Integer... values) {
        return fetch(User.USER.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.endsound.trpg.backend.player.jooq.tables.pojos.User fetchOneById(Integer value) {
        return fetchOne(User.USER.ID, value);
    }

    /**
     * Fetch records that have <code>username IN (values)</code>
     */
    public List<com.endsound.trpg.backend.player.jooq.tables.pojos.User> fetchByUsername(String... values) {
        return fetch(User.USER.USERNAME, values);
    }

    /**
     * Fetch a unique record that has <code>username = value</code>
     */
    public com.endsound.trpg.backend.player.jooq.tables.pojos.User fetchOneByUsername(String value) {
        return fetchOne(User.USER.USERNAME, value);
    }

    /**
     * Fetch records that have <code>password IN (values)</code>
     */
    public List<com.endsound.trpg.backend.player.jooq.tables.pojos.User> fetchByPassword(String... values) {
        return fetch(User.USER.PASSWORD, values);
    }

    /**
     * Fetch records that have <code>locked IN (values)</code>
     */
    public List<com.endsound.trpg.backend.player.jooq.tables.pojos.User> fetchByLocked(Boolean... values) {
        return fetch(User.USER.LOCKED, values);
    }

    /**
     * Fetch records that have <code>enable IN (values)</code>
     */
    public List<com.endsound.trpg.backend.player.jooq.tables.pojos.User> fetchByEnable(Boolean... values) {
        return fetch(User.USER.ENABLE, values);
    }

    /**
     * Fetch records that have <code>expired_date IN (values)</code>
     */
    public List<com.endsound.trpg.backend.player.jooq.tables.pojos.User> fetchByExpiredDate(Timestamp... values) {
        return fetch(User.USER.EXPIRED_DATE, values);
    }

    /**
     * Fetch records that have <code>pwd_expired_date IN (values)</code>
     */
    public List<com.endsound.trpg.backend.player.jooq.tables.pojos.User> fetchByPwdExpiredDate(Timestamp... values) {
        return fetch(User.USER.PWD_EXPIRED_DATE, values);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<com.endsound.trpg.backend.player.jooq.tables.pojos.User> fetchByName(String... values) {
        return fetch(User.USER.NAME, values);
    }
}
