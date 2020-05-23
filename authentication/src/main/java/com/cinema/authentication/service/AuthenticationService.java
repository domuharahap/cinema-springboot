package com.cinema.authentication.service;

import com.cinema.authentication.domain.Roles;
import com.cinema.authentication.domain.Users;
import com.cinema.authentication.repository.AuthenticationRepository;
import com.cinema.authentication.repository.RolesRepository;
import com.cinema.authentication.utils.RoleName;
import com.cinema.authentication.utils.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    AuthenticationRepository repository;

    @Autowired
    RolesRepository roleRepository;

    public UserDetails findByUsername(String username) {
        Users users = repository.findByUsername(username);
        return UserPrincipal.create(users);
    }

    public UserDetails findById(Long id) {
        Optional<Users> user = repository.findById(id);
        return UserPrincipal.create(user.get());

    }

    public boolean checkExistUsernameAndEmail(String username, String email) {
        System.out.println("username:"+username+", email:"+email);
        Users u = repository.findByUsernameOrEmail(username, email);
        if(u != null)
            return true;
        else
            return false;
    }

    public void saveUser(Users user) {
        repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = repository.findByUsername(username);
        return UserPrincipal.create(users);
    }

    public Optional<Roles> findRolesByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
