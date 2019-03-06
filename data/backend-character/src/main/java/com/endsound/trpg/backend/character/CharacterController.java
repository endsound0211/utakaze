package com.endsound.trpg.backend.character;

import com.endsound.trpg.backend.character.jooq.tables.pojos.Character;
import com.endsound.trpg.socket.handshake.bean.TokenPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;

@Controller
@MessageMapping("/backend/socket/server/utakaze/character")
public class CharacterController {
    @Autowired
    private CharacterService characterService;

    @MessageMapping("/list")
    @SendToUser("/backend/socket/client/utakaze/character/list")
    public List<Character> fetch(){
        return characterService.fetch();
    }

    @MessageMapping("/insert")
    @SendTo("/backend/socket/client/utakaze/character/insert")
    public Character insert(Character character, Principal principal){
        return characterService.insert(character.setBelongUserId(((TokenPrincipal) principal).getUser().getId()));
    }

    @MessageMapping("/update")
    @SendTo("/backend/socket/client/utakaze/character/update")
    public Character update(Character character){
        return characterService.update(character);
    }

    @MessageMapping("/delete")
    @SendTo("/backend/socket/client/utakaze/character/delete")
    public Character delete(Character character){
        return characterService.delete(character);
    }
}
