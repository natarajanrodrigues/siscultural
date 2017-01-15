package io.github.siscultural.services;

import io.github.siscultural.entities.*;
import io.github.siscultural.enums.ErrorMessages;
import io.github.siscultural.repositories.*;
import io.github.siscultural.utils.LocalDateToStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

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



    public Map<Program, Set<Contract>> contractsPerProgram(Committe committe) {

        Map<Program, Set<Contract>> test = new LinkedHashMap<>();

        if (committe != null) {

            //pega todos os contratos
            List<Contract> contracts = committe.getContracts();

            //separa os contratos por Programa
            Map<Program, Set<Contract>> result =
                    contracts.stream().collect(
                            Collectors.groupingBy(Contract::getProgram, Collectors.toSet() )
                    );

            //adiciona num novo map, ordenando as keys do map (pelo nome do programa)
            result.entrySet().stream()
                    .sorted((e1, e2) -> e1.getKey().getName().compareTo(e2.getKey().getName()))
                    .forEachOrdered(x -> test.put(x.getKey(), x.getValue()));

            result.clear();

            //ordenando cada um dos Set com os contratos
            for (Map.Entry<Program, Set<Contract>> entry: test.entrySet()) {

                SortedSet<Contract> newSet = new TreeSet<>(Contract.Comparators.PRESENTATION_NAME);
                newSet.addAll(entry.getValue());

                test.put(entry.getKey(), newSet);

            }
        }

        return test;
    }


//    public void removeContract(Committe committe, Contract contract) {
//
//        committe.removeContract(contract);
//        contractRepository.save(contract);
//
//    }




}
