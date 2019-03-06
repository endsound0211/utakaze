package com.endsound.trpg.security.bean;

import java.sql.Timestamp;
import java.util.List;

public class JwtPayload<T> {
    private String subject;
    private String audience;
    private Timestamp refresh;
    private Timestamp expiration;
    private Timestamp issuedAt;
    private String issuer;
    private T data;
    private List<String> roles;

    public List<String> getRoles() {
        return roles;
    }

    public JwtPayload setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public JwtPayload setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getAudience() {
        return audience;
    }

    public JwtPayload setAudience(String audience) {
        this.audience = audience;
        return this;
    }

    public Timestamp getRefresh() {
        return refresh;
    }

    public JwtPayload setRefresh(Timestamp refresh) {
        this.refresh = refresh;
        return this;
    }

    public Timestamp getExpiration() {
        return expiration;
    }

    public JwtPayload setExpiration(Timestamp expiration) {
        this.expiration = expiration;
        return this;
    }

    public Timestamp getIssuedAt() {
        return issuedAt;
    }

    public JwtPayload setIssuedAt(Timestamp issuedAt) {
        this.issuedAt = issuedAt;
        return this;
    }

    public String getIssuer() {
        return issuer;
    }

    public JwtPayload setIssuer(String issuer) {
        this.issuer = issuer;
        return this;
    }

    public T getData() {
        return data;
    }

    public JwtPayload setData(T data) {
        this.data = data;
        return this;
    }
}
