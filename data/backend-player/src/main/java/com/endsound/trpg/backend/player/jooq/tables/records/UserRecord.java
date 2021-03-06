/*
 * This file is generated by jOOQ.
 */
package com.endsound.trpg.backend.player.jooq.tables.records;


import com.endsound.trpg.backend.player.jooq.tables.User;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;


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
public class UserRecord extends UpdatableRecordImpl<UserRecord> implements Record8<Integer, String, String, Boolean, Boolean, Timestamp, Timestamp, String> {

    private static final long serialVersionUID = 1958636232;

    /**
     * Setter for <code>utakaze.user.id</code>.
     */
    public UserRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>utakaze.user.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>utakaze.user.username</code>. 使用者名稱
     */
    public UserRecord setUsername(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>utakaze.user.username</code>. 使用者名稱
     */
    public String getUsername() {
        return (String) get(1);
    }

    /**
     * Setter for <code>utakaze.user.password</code>. 密碼
     */
    public UserRecord setPassword(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>utakaze.user.password</code>. 密碼
     */
    public String getPassword() {
        return (String) get(2);
    }

    /**
     * Setter for <code>utakaze.user.locked</code>.
     */
    public UserRecord setLocked(Boolean value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>utakaze.user.locked</code>.
     */
    public Boolean getLocked() {
        return (Boolean) get(3);
    }

    /**
     * Setter for <code>utakaze.user.enable</code>.
     */
    public UserRecord setEnable(Boolean value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>utakaze.user.enable</code>.
     */
    public Boolean getEnable() {
        return (Boolean) get(4);
    }

    /**
     * Setter for <code>utakaze.user.expired_date</code>. 過期時間
     */
    public UserRecord setExpiredDate(Timestamp value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>utakaze.user.expired_date</code>. 過期時間
     */
    public Timestamp getExpiredDate() {
        return (Timestamp) get(5);
    }

    /**
     * Setter for <code>utakaze.user.pwd_expired_date</code>. 密碼過期時間
     */
    public UserRecord setPwdExpiredDate(Timestamp value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>utakaze.user.pwd_expired_date</code>. 密碼過期時間
     */
    public Timestamp getPwdExpiredDate() {
        return (Timestamp) get(6);
    }

    /**
     * Setter for <code>utakaze.user.name</code>. 名稱
     */
    public UserRecord setName(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>utakaze.user.name</code>. 名稱
     */
    public String getName() {
        return (String) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<Integer, String, String, Boolean, Boolean, Timestamp, Timestamp, String> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<Integer, String, String, Boolean, Boolean, Timestamp, Timestamp, String> valuesRow() {
        return (Row8) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return User.USER.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return User.USER.USERNAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return User.USER.PASSWORD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field4() {
        return User.USER.LOCKED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Boolean> field5() {
        return User.USER.ENABLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field6() {
        return User.USER.EXPIRED_DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field7() {
        return User.USER.PWD_EXPIRED_DATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return User.USER.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getUsername();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component3() {
        return getPassword();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component4() {
        return getLocked();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean component5() {
        return getEnable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component6() {
        return getExpiredDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component7() {
        return getPwdExpiredDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component8() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getUsername();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getPassword();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value4() {
        return getLocked();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean value5() {
        return getEnable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value6() {
        return getExpiredDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value7() {
        return getPwdExpiredDate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value2(String value) {
        setUsername(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value3(String value) {
        setPassword(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value4(Boolean value) {
        setLocked(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value5(Boolean value) {
        setEnable(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value6(Timestamp value) {
        setExpiredDate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value7(Timestamp value) {
        setPwdExpiredDate(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value8(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord values(Integer value1, String value2, String value3, Boolean value4, Boolean value5, Timestamp value6, Timestamp value7, String value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UserRecord
     */
    public UserRecord() {
        super(User.USER);
    }

    /**
     * Create a detached, initialised UserRecord
     */
    public UserRecord(Integer id, String username, String password, Boolean locked, Boolean enable, Timestamp expiredDate, Timestamp pwdExpiredDate, String name) {
        super(User.USER);

        set(0, id);
        set(1, username);
        set(2, password);
        set(3, locked);
        set(4, enable);
        set(5, expiredDate);
        set(6, pwdExpiredDate);
        set(7, name);
    }
}
