package com.portfolio.id.bor.Security.Service;

import com.portfolio.id.bor.Security.Entity.Role;
import com.portfolio.id.bor.Security.Enum.RoleName;
import com.portfolio.id.bor.Security.Repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RoleService {
    @Autowired
    IRoleRepository iRoleRepository;

    public Optional<Role> getByRoleName(RoleName roleName){
        return iRoleRepository.findByRoleName(roleName);
    }

    public void save(Role role){
        iRoleRepository.save(role);
    }
}
