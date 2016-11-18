package io.github.siscultural.services;

import io.github.siscultural.entities.SpecialEvent;
import io.github.siscultural.enums.ErrorMessages;
import io.github.siscultural.repositories.SpecialEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Natarajan Rodrigues on 18/11/2016.
 */
@Service
@Transactional
public class SpecialEventService {

    @Autowired
    private static SpecialEventRepository specialEventRepository;

    @Autowired
    public SpecialEventService(SpecialEventRepository specialEventRepository) {
        this.specialEventRepository = specialEventRepository;
    }

    public SpecialEvent findById(Long id) {
        return specialEventRepository.findById(id);
    }

    public List<SpecialEvent> findAll () {
        return specialEventRepository.findAll();
    }

    public SpecialEvent save (SpecialEvent SpecialEvent) {

        return specialEventRepository.save(SpecialEvent);
    }

    public Map<String, String> delete(SpecialEvent SpecialEvent) {

        Long id = SpecialEvent.getId();

        Map<String, String> map = new HashMap<>();
        map.clear();

        if (SpecialEvent != null) {

            specialEventRepository.delete(SpecialEvent);

            SpecialEvent = specialEventRepository.findById(id);

            if (SpecialEvent != null) {
                map.put("erro", ErrorMessages.ERROR_OPERATION.toString());
            } else {
                map.put("resultado", "Exclusão realizada com sucesso.");
            }
        } else {
            map.put("error", "Evento especial não encontrada.");
        }

        return map;
    }


}
