package io.github.siscultural.services;

import io.github.siscultural.entities.AdministrationUnity;
import io.github.siscultural.entities.Locality;
import io.github.siscultural.enums.ErrorMessages;
import io.github.siscultural.repositories.AdministrationUnityRepository;
import io.github.siscultural.repositories.LocalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by susanneferraz on 16/09/16.
 */
@Service
@Transactional
public class AdministrationUnityService {

    @Autowired
    private AdministrationUnityRepository administrationUnityRepository;

    @Autowired
    public AdministrationUnityService(AdministrationUnityRepository administrationUnityRepository) {
        this.administrationUnityRepository = administrationUnityRepository;
    }

    public AdministrationUnity findById(Long id) {
        return administrationUnityRepository.findById(id);
    }

    public List<AdministrationUnity> findAll () {
        return administrationUnityRepository.findAll();
    }

    public AdministrationUnity save (AdministrationUnity administrationUnity) {

        return administrationUnityRepository.save(administrationUnity);
    }




}
