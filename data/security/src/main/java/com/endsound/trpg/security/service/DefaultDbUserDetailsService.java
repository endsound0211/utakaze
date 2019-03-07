package com.endsound.trpg.security.service;

import com.endsound.trpg.security.bean.DefaultGrantedAuthority;
import com.endsound.trpg.security.bean.DefaultUserDetail;
import com.endsound.trpg.security.dao.RoleDao;
import com.endsound.trpg.security.dao.UserDao;
import com.endsound.trpg.security.jooq.tables.pojos.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class DefaultDbUserDetailsService implements UserDetailsService {
    private UserDao userDao;
    private RoleDao roleDao;

    public DefaultDbUserDetailsService(UserDao userDao, RoleDao roleDao){
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = Optional.ofNullable(userDao.fetchOneByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("帳號密碼錯誤"));

        List<String> roles = roleDao.fetchRolesByUsername(username);

        DefaultUserDetail userDetail = new DefaultUserDetail(
                user.getUsername(),
                user.getPassword(),
                roles.stream().map(role -> new DefaultGrantedAuthority(role)).collect(Collectors.toList()))
                .setEnable(user.getEnable())
                .setLocked(user.getLocked())
                .setExpiredDate(user.getExpiredDate())
                .setPwdExpiredDate(user.getPwdExpiredDate())
                .setData(user.setPassword(null));

        return userDetail;
    }
}
