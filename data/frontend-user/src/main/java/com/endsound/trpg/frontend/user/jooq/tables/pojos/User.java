/*
 * This file is generated by jOOQ.
 */
package com.endsound.trpg.frontend.user.jooq.tables.pojos;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;


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
public class User implements Serializable {

    private static final long serialVersionUID = -1558564930;

    private Integer   id;
    private String    username;
    private String    password;
    private Boolean   locked;
    private Boolean   enable;
    private Timestamp expiredDate;
    private Timestamp pwdExpiredDate;
    private String    name;

    public User() {}

    public User(User value) {
        this.id = value.id;
        this.username = value.username;
        this.password = value.password;
        this.locked = value.locked;
        this.enable = value.enable;
        this.expiredDate = value.expiredDate;
        this.pwdExpiredDate = value.pwdExpiredDate;
        this.name = value.name;
    }

    public User(
        Integer   id,
        String    username,
        String    password,
        Boolean   locked,
        Boolean   enable,
        Timestamp expiredDate,
        Timestamp pwdExpiredDate,
        String    name
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.locked = locked;
        this.enable = enable;
        this.expiredDate = expiredDate;
        this.pwdExpiredDate = pwdExpiredDate;
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return this.username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Boolean getLocked() {
        return this.locked;
    }

    public User setLocked(Boolean locked) {
        this.locked = locked;
        return this;
    }

    public Boolean getEnable() {
        return this.enable;
    }

    public User setEnable(Boolean enable) {
        this.enable = enable;
        return this;
    }

    public Timestamp getExpiredDate() {
        return this.expiredDate;
    }

    public User setExpiredDate(Timestamp expiredDate) {
        this.expiredDate = expiredDate;
        return this;
    }

    public Timestamp getPwdExpiredDate() {
        return this.pwdExpiredDate;
    }

    public User setPwdExpiredDate(Timestamp pwdExpiredDate) {
        this.pwdExpiredDate = pwdExpiredDate;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("User (");

        sb.append(id);
        sb.append(", ").append(username);
        sb.append(", ").append(password);
        sb.append(", ").append(locked);
        sb.append(", ").append(enable);
        sb.append(", ").append(expiredDate);
        sb.append(", ").append(pwdExpiredDate);
        sb.append(", ").append(name);

        sb.append(")");
        return sb.toString();
    }
}
