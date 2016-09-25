/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.services;

import io.github.siscultural.entities.CompanyProvider;
import io.github.siscultural.entities.IndividualProvider;
import io.github.siscultural.entities.Provider;
import io.github.siscultural.repositories.ProviderRepository;
import java.util.ArrayList;
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
    private ProviderRepository providerRepository;

    public List<IndividualProvider> findAllIndividualProvider() {

        List<IndividualProvider> result = new ArrayList<>();

        providerRepository.findByType("IndividualProvider").forEach(provider -> result.add((IndividualProvider) provider));

        return result;
    }

    public List<CompanyProvider> findAllCompanyProvider() {

        List<CompanyProvider> result = new ArrayList<>();

        providerRepository.findByType("CompanyProvider").forEach(provider -> result.add((CompanyProvider) provider));

        return result;
    }

    public void save(Provider provider) {

        providerRepository.save(provider);
    }
    
    public void remove(long providerId){
        
        providerRepository.delete(providerId);
    }
    
}
