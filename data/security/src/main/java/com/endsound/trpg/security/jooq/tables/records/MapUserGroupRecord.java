/*
 * This file is generated by jOOQ.
 */
package com.endsound.trpg.security.jooq.tables.records;


import com.endsound.trpg.security.jooq.tables.MapUserGroup;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * 使用者群組對應
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MapUserGroupRecord extends UpdatableRecordImpl<MapUserGroupRecord> implements Record2<Integer, Integer> {

    private static final long serialVersionUID = 608440837;

    /**
     * Setter for <code>utakaze.map_user_group.user_id</code>.
     */
    public MapUserGroupRecord setUserId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>utakaze.map_user_group.user_id</code>.
     */
    public Integer getUserId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>utakaze.map_user_group.group_id</code>.
     */
    public MapUserGroupRecord setGroupId(Integer value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>utakaze.map_user_group.group_id</code>.
     */
    public Integer getGroupId() {
        return (Integer) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record2<Integer, Integer> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Integer, Integer> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Integer, Integer> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return MapUserGroup.MAP_USER_GROUP.USER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return MapUserGroup.MAP_USER_GROUP.GROUP_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component2() {
        return getGroupId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return getGroupId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MapUserGroupRecord value1(Integer value) {
        setUserId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MapUserGroupRecord value2(Integer value) {
        setGroupId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MapUserGroupRecord values(Integer value1, Integer value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MapUserGroupRecord
     */
    public MapUserGroupRecord() {
        super(MapUserGroup.MAP_USER_GROUP);
    }

    /**
     * Create a detached, initialised MapUserGroupRecord
     */
    public MapUserGroupRecord(Integer userId, Integer groupId) {
        super(MapUserGroup.MAP_USER_GROUP);

        set(0, userId);
        set(1, groupId);
    }
}
