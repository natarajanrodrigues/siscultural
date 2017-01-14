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

import java.math.BigInteger;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

//    static Pageable pageablebyName = new PageRequest(0, 20, new Sort(new Sort.Order(Sort.Direction.ASC, "name")));

    public Page<Provider> findAll(Pageable pageable) {

        return providerRepository.findAll(pageable);
    }

    public List<Provider> findAll() {
        List<Provider> providers = providerRepository.findAll();

        Collections.sort(providers, Provider.Comparators.NAME);

        return providers;
    }

    public Page<Provider> findByName(String name, Pageable pageable) {

        return providerRepository.findByNameIgnoreCaseContaining(name, pageable);
    }

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


    public Map<String, String> save(Provider provider) {

        Map<String, String> result = new HashMap<>();

        if (provider.getId() != null) {

            Provider p = providerRepository.save(provider);
            if (p != null)
                result.put("ok", "Atualizado com sucesso");
            else
                result.put("erro", "Erro ao atualizar cadastro do fornecedor");
        } else {

            String code = provider.getCode();

            List<Provider> providers = providerRepository.findByCpfOrCNPJ(code);

            if (providers != null && providers.size() > 0) {

                result.put("erro", "Já existe fornecedor com o CNPJ / CPF cadastrado");

            } else {

                Provider p = providerRepository.save(provider);
                if (p != null)
                    result.put("ok", "Cadastrado com sucesso");
            }
        }

        return result;

    }
    
    public void remove(long providerId){
        
        providerRepository.delete(providerId);
    }


    public List<Provider> getAllProviders(){
        List<Provider> result = new ArrayList<>();

        result.addAll(findAllCompanyProvider());
        result.addAll(findAllIndividualProvider());

        return result;
    }

    public Provider findbyId(Long id) {
        return providerRepository.findById(id);
    }
    
    public Provider findById(long id) {
        
        return providerRepository.findById(id);
    }

    public void update(Provider provider) {
        
        providerRepository.save(provider);
    }

}
