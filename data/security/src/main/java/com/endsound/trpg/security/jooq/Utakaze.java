/*
 * This file is generated by jOOQ.
 */
package com.endsound.trpg.security.jooq;


import com.endsound.trpg.security.jooq.tables.Group;
import com.endsound.trpg.security.jooq.tables.MapGroupRole;
import com.endsound.trpg.security.jooq.tables.MapUserGroup;
import com.endsound.trpg.security.jooq.tables.Role;
import com.endsound.trpg.security.jooq.tables.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Utakaze extends SchemaImpl {

    private static final long serialVersionUID = 1121271078;

    /**
     * The reference instance of <code>utakaze</code>
     */
    public static final Utakaze UTAKAZE = new Utakaze();

    /**
     * 群組
     */
    public final Group GROUP = com.endsound.trpg.security.jooq.tables.Group.GROUP;

    /**
     * The table <code>utakaze.map_group_role</code>.
     */
    public final MapGroupRole MAP_GROUP_ROLE = com.endsound.trpg.security.jooq.tables.MapGroupRole.MAP_GROUP_ROLE;

    /**
     * 使用者群組對應
     */
    public final MapUserGroup MAP_USER_GROUP = com.endsound.trpg.security.jooq.tables.MapUserGroup.MAP_USER_GROUP;

    /**
     * 角色
     */
    public final Role ROLE = com.endsound.trpg.security.jooq.tables.Role.ROLE;

    /**
     * 使用者資訊
     */
    public final User USER = com.endsound.trpg.security.jooq.tables.User.USER;

    /**
     * No further instances allowed
     */
    private Utakaze() {
        super("utakaze", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Group.GROUP,
            MapGroupRole.MAP_GROUP_ROLE,
            MapUserGroup.MAP_USER_GROUP,
            Role.ROLE,
            User.USER);
    }
}
