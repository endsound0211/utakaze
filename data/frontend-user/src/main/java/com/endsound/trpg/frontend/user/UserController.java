package com.endsound.trpg.frontend.user;

import com.endsound.trpg.frontend.user.jooq.tables.pojos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController("frontend-user-controller")
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    public void register(@RequestBody User user) throws NoSuchAlgorithmException {
        userService.registry(user);
    }
}
