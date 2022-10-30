package com.portfolio.id.bor.Security.Service;

import com.portfolio.id.bor.Security.Entity.User;
import com.portfolio.id.bor.Security.Entity.UserMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImplService implements UserDetailsService {
    @Autowired
    UserSecurityService userSecurityService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userSecurityService.getByUserName(userName).get();
        return UserMain.build(user);
    }
}
