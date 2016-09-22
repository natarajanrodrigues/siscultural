/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.services;

import io.github.siscultural.entities.CompanyProvider;
import io.github.siscultural.entities.IndividualProvider;
import io.github.siscultural.repositories.CompanyProviderRepository;
import io.github.siscultural.repositories.IndividualProviderRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
@Service
@Transactional
public class ProviderService {

    @Autowired
    private IndividualProviderRepository ipRepository;

    @Autowired
    private CompanyProviderRepository cpRepository;

    public List<IndividualProvider> findAllIndividualProvider() {

        return ipRepository.findAll();
    }

    public List<CompanyProvider> findAllCompanyProvider() {

        return cpRepository.findAll();
    }
    
}
