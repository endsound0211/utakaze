package com.endsound.trpg.security.bean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class DefaultUserDetail implements UserDetails {
    private String username;
    private String password;
    private List<? extends GrantedAuthority> authorities;
    private Boolean locked;
    private Boolean enable;
    private Timestamp expiredDate;
    private Timestamp pwdExpiredDate;
    private Object data;


    public DefaultUserDetail(String username, String password, List<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public List<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        if (Objects.nonNull(expiredDate))
            return Timestamp.from(Instant.now()).before(expiredDate);
        else
            return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        if (Objects.nonNull(pwdExpiredDate))
            return Timestamp.from(Instant.now()).before(pwdExpiredDate);
        else
            return true;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }

    public DefaultUserDetail setLocked(Boolean locked) {
        this.locked = locked;
        return this;
    }

    public DefaultUserDetail setEnable(Boolean enable) {
        this.enable = enable;
        return this;
    }

    public DefaultUserDetail setExpiredDate(Timestamp expiredDate) {
        this.expiredDate = expiredDate;
        return this;
    }

    public DefaultUserDetail setPwdExpiredDate(Timestamp pwdExpiredDate) {
        this.pwdExpiredDate = pwdExpiredDate;
        return this;
    }

    public Object getData() {
        return data;
    }

    public DefaultUserDetail setData(Object data) {
        this.data = data;
        return this;
    }
}
