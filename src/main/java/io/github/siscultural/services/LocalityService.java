package io.github.siscultural.services;

import io.github.siscultural.entities.Locality;
import io.github.siscultural.entities.Presentation;
import io.github.siscultural.enums.ErrorMessages;
import io.github.siscultural.repositories.LocalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by susanneferraz on 16/09/16.
 */
@Service
@Transactional
public class LocalityService {

    @Autowired
    private static LocalityRepository localityRepository;

    @Autowired
    public LocalityService(LocalityRepository localityRepository) {
        this.localityRepository = localityRepository;
    }

    public Locality findById(Long id) {
        return localityRepository.findById(id);
    }

    public List<Locality> findAll () {
        return localityRepository.findAll();
    }

    public Locality save (Locality locality) {

        return localityRepository.save(locality);
    }

    public Map<String, String> delete(Locality locality) {

        Long id = locality.getId();

        Map<String, String> map = new HashMap<>();
        map.clear();

        if (locality != null) {

            localityRepository.delete(locality);

            locality = localityRepository.findById(id);

            if (locality != null) {
                map.put("erro", ErrorMessages.ERROR_OPERATION.toString());
            } else {
                map.put("resultado", "Exclusão realizada com sucesso.");
            }
        } else {
            map.put("error", "Localidade não encontrada.");
        }

        return map;
    }


}
