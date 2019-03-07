package com.endsound.trpg.frontend.user;

import com.endsound.trpg.frontend.user.jooq.tables.pojos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("frontend-user-service")
public class UserService {
    @Autowired
    private UserDao userDao;

    public void registry(User user){
        userDao.insert(user);
    }
}
