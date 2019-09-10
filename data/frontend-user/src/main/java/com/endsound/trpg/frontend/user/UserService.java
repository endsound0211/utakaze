package com.endsound.trpg.frontend.user;

import com.endsound.trpg.frontend.user.jooq.tables.pojos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service("frontend-user-service")
public class UserService {
    @Autowired
    private UserDao userDao;

    public void registry(User user) throws NoSuchAlgorithmException {
        String password = Base64.getEncoder()
                .encodeToString(MessageDigest.getInstance("MD5").digest(user.getPassword().getBytes()));
        userDao.insert(user.setPassword(password));
    }
}
