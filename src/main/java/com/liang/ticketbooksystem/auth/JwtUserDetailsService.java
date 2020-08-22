package com.liang.ticketbooksystem.auth;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liang.ticketbooksystem.pojo.User;
import com.liang.ticketbooksystem.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserServiceImpl userServiceImp;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        System.out.println("JwtUserDetailsService:" + username);
        List<GrantedAuthority> authorityList = new ArrayList<>();
//        from database to get role
        authorityList.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        queryWrapper.clear();
        queryWrapper.eq("username",username);
        User user= userServiceImp.getOne(queryWrapper);
        return new SecurityUserDetails(user.getUsername(),user.getPassword(),authorityList);
    }

}