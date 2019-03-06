package com.endsound.trpg.backend.player;

public class PlayerBean {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public PlayerBean setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PlayerBean setName(String name) {
        this.name = name;
        return this;
    }
}
