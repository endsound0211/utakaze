package com.endsound.trpg.backend.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@MessageMapping("/backend/socket/server/utakaze/player")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @MessageMapping("/list")
    @SendToUser("/backend/socket/client/utakaze/player/list")
    public List<PlayerBean> fetch(){
        return playerService.fetch();
    }

    @MessageMapping("/join")
    @SendTo("/backend/socket/client/utakaze/player/join")
    public PlayerBean join(PlayerBean playerBean){
        return playerBean;
    }
}
