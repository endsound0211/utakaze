/*
 * This file is generated by jOOQ.
 */
package com.endsound.trpg.security.jooq.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


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
public class MapGroupRole implements Serializable {

    private static final long serialVersionUID = -1456859097;

    private Integer groupId;
    private Integer roleId;

    public MapGroupRole() {}

    public MapGroupRole(MapGroupRole value) {
        this.groupId = value.groupId;
        this.roleId = value.roleId;
    }

    public MapGroupRole(
        Integer groupId,
        Integer roleId
    ) {
        this.groupId = groupId;
        this.roleId = roleId;
    }

    public Integer getGroupId() {
        return this.groupId;
    }

    public MapGroupRole setGroupId(Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public MapGroupRole setRoleId(Integer roleId) {
        this.roleId = roleId;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MapGroupRole (");

        sb.append(groupId);
        sb.append(", ").append(roleId);

        sb.append(")");
        return sb.toString();
    }
}
