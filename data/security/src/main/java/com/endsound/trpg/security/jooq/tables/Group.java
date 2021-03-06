/*
 * This file is generated by jOOQ.
 */
package com.endsound.trpg.security.jooq.tables;


import com.endsound.trpg.security.jooq.Indexes;
import com.endsound.trpg.security.jooq.Keys;
import com.endsound.trpg.security.jooq.Utakaze;
import com.endsound.trpg.security.jooq.tables.records.GroupRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * 群組
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Group extends TableImpl<GroupRecord> {

    private static final long serialVersionUID = -1387965194;

    /**
     * The reference instance of <code>utakaze.group</code>
     */
    public static final Group GROUP = new Group();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<GroupRecord> getRecordType() {
        return GroupRecord.class;
    }

    /**
     * The column <code>utakaze.group.id</code>.
     */
    public final TableField<GroupRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>utakaze.group.name</code>. 名稱
     */
    public final TableField<GroupRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR(100).nullable(false), this, "名稱");

    /**
     * Create a <code>utakaze.group</code> table reference
     */
    public Group() {
        this(DSL.name("group"), null);
    }

    /**
     * Create an aliased <code>utakaze.group</code> table reference
     */
    public Group(String alias) {
        this(DSL.name(alias), GROUP);
    }

    /**
     * Create an aliased <code>utakaze.group</code> table reference
     */
    public Group(Name alias) {
        this(alias, GROUP);
    }

    private Group(Name alias, Table<GroupRecord> aliased) {
        this(alias, aliased, null);
    }

    private Group(Name alias, Table<GroupRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("群組"));
    }

    public <O extends Record> Group(Table<O> child, ForeignKey<O, GroupRecord> key) {
        super(child, key, GROUP);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Utakaze.UTAKAZE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.GROUP_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<GroupRecord, Integer> getIdentity() {
        return Keys.IDENTITY_GROUP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<GroupRecord> getPrimaryKey() {
        return Keys.KEY_GROUP_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<GroupRecord>> getKeys() {
        return Arrays.<UniqueKey<GroupRecord>>asList(Keys.KEY_GROUP_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Group as(String alias) {
        return new Group(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Group as(Name alias) {
        return new Group(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Group rename(String name) {
        return new Group(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Group rename(Name name) {
        return new Group(name, null);
    }
}
