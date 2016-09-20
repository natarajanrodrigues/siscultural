/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural;

import io.github.siscultural.entities.Functionary;
import io.github.siscultural.repositories.FunctionaryRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
@Service
@Transactional
public class FunctionaryDetailsService implements UserDetailsService {

    @Autowired
    private FunctionaryRepository functionaryRepository;

    @Override
    public FunctionaryUser loadUserByUsername(String email) throws UsernameNotFoundException {
        
        Functionary functionary = functionaryRepository.findByEmail(email);
        
        if (functionary == null) {
            throw new UsernameNotFoundException("No user found with e-mail: " + email);
        }
        
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(functionary.getUserType().name()));
        
        FunctionaryUser user = new FunctionaryUser(functionary, true, true, true, true, authorities);
        
        return user;
    }
    
}
