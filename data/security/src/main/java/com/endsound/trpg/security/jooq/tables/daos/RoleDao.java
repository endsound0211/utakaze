/*
 * This file is generated by jOOQ.
 */
package com.endsound.trpg.security.jooq.tables.daos;


import com.endsound.trpg.security.jooq.tables.Role;
import com.endsound.trpg.security.jooq.tables.records.RoleRecord;

import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * 角色
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RoleDao extends DAOImpl<RoleRecord, com.endsound.trpg.security.jooq.tables.pojos.Role, Integer> {

    /**
     * Create a new RoleDao without any configuration
     */
    public RoleDao() {
        super(Role.ROLE, com.endsound.trpg.security.jooq.tables.pojos.Role.class);
    }

    /**
     * Create a new RoleDao with an attached configuration
     */
    public RoleDao(Configuration configuration) {
        super(Role.ROLE, com.endsound.trpg.security.jooq.tables.pojos.Role.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(com.endsound.trpg.security.jooq.tables.pojos.Role object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.endsound.trpg.security.jooq.tables.pojos.Role> fetchById(Integer... values) {
        return fetch(Role.ROLE.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.endsound.trpg.security.jooq.tables.pojos.Role fetchOneById(Integer value) {
        return fetchOne(Role.ROLE.ID, value);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<com.endsound.trpg.security.jooq.tables.pojos.Role> fetchByName(String... values) {
        return fetch(Role.ROLE.NAME, values);
    }
}
