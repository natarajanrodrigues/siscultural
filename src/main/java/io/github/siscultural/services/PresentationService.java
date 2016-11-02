package io.github.siscultural.services;

import io.github.siscultural.entities.Presentation;
import io.github.siscultural.enums.ErrorMessages;
import io.github.siscultural.repositories.PresentationRepository;
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
public class PresentationService {

    @Autowired
    private PresentationRepository presentationRepository;

    @Autowired
    public PresentationService(PresentationRepository presentationRepository) {
        this.presentationRepository = presentationRepository;
    }

    public Presentation findById(Long id) {
        return presentationRepository.findById(id);
    }

    public List<Presentation> findAll () {
        return presentationRepository.findAll();
    }

    public Presentation save (Presentation presentation) {

        return presentationRepository.save(presentation);
    }

    public Map<String, String> delete(Presentation presentation) {

        Long id = presentation.getId();

        Map<String, String> map = new HashMap<>();
        map.clear();

        if (presentation != null) {

            presentationRepository.delete(presentation);

            presentation = presentationRepository.findById(id);

            if (presentation != null) {
                map.put("erro", ErrorMessages.ERROR_OPERATION.toString());
            } else {
                map.put("resultado", "Exclusão realizada com sucesso.");
            }
        } else {
            map.put("error", "Apresentação não encontrada.");
        }

        return map;
    }


}
