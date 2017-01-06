package io.github.siscultural.services;

import io.github.siscultural.entities.*;
import io.github.siscultural.enums.ErrorMessages;
import io.github.siscultural.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by natarajan on 05/01/2017.
 */
@Service
public class CommitteService {

    @Autowired
    private ContractRepository contractRepository;
    private CommitteRepository committeRepository;

    @Autowired
    public CommitteService(ContractRepository contractRepository,
                           CommitteRepository committeRepository) {

        this.contractRepository = contractRepository;
        this.committeRepository = committeRepository;
    }

    public Committe findById(Long id) {
        return committeRepository.findById(id);
    }

    public List<Committe> findAll () {
        return committeRepository.findAll();
    }

    public Committe save (Committe committe) {

        return committeRepository.save(committe);
    }

    @Transactional
    public Map<String, String> delete(Committe committe) {

        Long id = committe.getId();

        Map<String, String> map = new HashMap<>();
        map.clear();

        if (committe != null) {

            //buscando contratos do comitê
            List<Contract> contracts = contractRepository.findByCommitte(committe);

            //retirando os contratos do comitê
            for (Contract c : contracts){
                c.setCommitte(null); // acho que vai dar problema aqui
//                removeContract(committe, c);
            }

            //apagando o comitê
            committeRepository.delete(committe);

            committe = committeRepository.findById(id);

            if (committe != null) {
                map.put("erro", ErrorMessages.ERROR_OPERATION.toString());
            } else {
                map.put("resultado", "Exclusão realizada com sucesso.");
            }
        } else {
            map.put("error", "Comitê de Aprovação não encontrado.");
        }

        return map;
    }

    public List<Contract> findByCommitte(Committe committe) {
        return contractRepository.findByCommitte(committe);
    }


//    public void removeContract(Committe committe, Contract contract) {
//
//        committe.removeContract(contract);
//        contractRepository.save(contract);
//
//    }




}
