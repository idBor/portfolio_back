package com.portfolio.id.bor.Security.Service;

import com.portfolio.id.bor.Security.Entity.User;
import com.portfolio.id.bor.Security.Repository.IUserSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserSecurityService {
    @Autowired
    IUserSecurityRepository iUserSecurityRepository;

    public Optional<User> getByUserName(String userName){
        return iUserSecurityRepository.findByUserName(userName);
    }

    public boolean existsByUserName(String userName){
        return iUserSecurityRepository.existsByUserName(userName);
    }

    public boolean existsByEmail(String email){
        return iUserSecurityRepository.existsByEmail(email);
    }

    public void save(User user){
        iUserSecurityRepository.save(user);
    }
}
