package com.endsound.trpg.security.dao;

import com.endsound.trpg.security.jooq.Tables;
import com.endsound.trpg.security.jooq.tables.MapGroupRole;
import com.endsound.trpg.security.jooq.tables.MapUserGroup;
import com.endsound.trpg.security.jooq.tables.Role;
import com.endsound.trpg.security.jooq.tables.User;
import org.jooq.Configuration;
import org.jooq.impl.DSL;

import java.util.List;

public class RoleDao extends com.endsound.trpg.security.jooq.tables.daos.RoleDao {
    public RoleDao(Configuration configuration){
        super(configuration);
    }

    public List<String> fetchRolesByUsername(String username) {
        Role role = Tables.ROLE;
        User user = Tables.USER;
        MapUserGroup mapUserGroup = Tables.MAP_USER_GROUP;
        MapGroupRole mapGroupRole = Tables.MAP_GROUP_ROLE;

        return DSL.using(configuration())
                .selectDistinct(role.NAME)
                .from(mapUserGroup)
                .innerJoin(mapGroupRole)
                .on(mapUserGroup.GROUP_ID.eq(mapGroupRole.GROUP_ID))
                .innerJoin(role)
                .on(mapGroupRole.ROLE_ID.eq(role.ID))
                .where(mapUserGroup.USER_ID.eq(
                        DSL.select(user.ID).from(user).where(user.USERNAME.eq(username))
                ))
                .fetchInto(String.class);
    }
}
