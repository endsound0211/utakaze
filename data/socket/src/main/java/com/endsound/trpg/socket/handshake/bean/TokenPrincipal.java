package com.endsound.trpg.socket.handshake.bean;

import com.endsound.trpg.security.jooq.tables.pojos.User;

import java.security.Principal;

public class TokenPrincipal implements Principal {
    private User user;

    @Override
    public String getName() {
        return user.getUsername();
    }

    public User getUser() {
        return user;
    }

    public TokenPrincipal setUser(User user) {
        this.user = user;
        return this;
    }
}
