package com.endsound.trpg.backend.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    @Autowired
    private UserDao userDao;

    public List<PlayerBean> fetch(){
        return userDao.findAll().stream()
                .map(user -> new PlayerBean()
                    .setId(user.getId())
                    .setName(user.getName()))
                .collect(Collectors.toList());
    }
}
