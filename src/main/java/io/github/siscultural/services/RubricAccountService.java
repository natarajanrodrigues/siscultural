package io.github.siscultural.services;

import io.github.siscultural.entities.Budget;
import io.github.siscultural.entities.Program;
import io.github.siscultural.entities.RubricAccount;
import io.github.siscultural.enums.ErrorMessages;
import io.github.siscultural.repositories.RubricAccountRepository;
import io.github.siscultural.repositories.RubricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Natarajan Rodrigues on 16/09/16.
 */
@Service
@Transactional
public class RubricAccountService {

    @Autowired
    private RubricAccountRepository rubricAccountRepository;

    @Autowired
    private BudgetService budgetService;

    @Autowired
    public RubricAccountService(RubricAccountRepository rubricAccountRepository, BudgetService budgetService) {
        this.rubricAccountRepository = rubricAccountRepository;
        this.budgetService = budgetService;
    }

    public List<RubricAccount> getRubricAccounts(Program program) {

//        return rubricAccountRepository.findByBudgetAndProgram(budgetService.getCurrentBudget(), program);
        List<RubricAccount> accounts = rubricAccountRepository.findAll();
        Budget currentBudget = budgetService.getCurrentBudget();

        List<RubricAccount> result = new LinkedList<>();

        for (RubricAccount ra : accounts) {
            if (ra.getBudget().equals(currentBudget) && ra.getRubric().getProgram().equals(program)) {
                result.add(ra);
            }
        }

        return result;

    }

    public RubricAccount findById(Long id) {
        return rubricAccountRepository.findById(id);
    }

    public List<RubricAccount> findAll () {
        return rubricAccountRepository.findAll();
    }

    public RubricAccount save (RubricAccount rubricAccount) {

        return rubricAccountRepository.save(rubricAccount);
    }

    public Map<String, String> delete(RubricAccount rubricAccount) {

        Long id = rubricAccount.getId();

        Map<String, String> map = new HashMap<>();
        map.clear();

        if (rubricAccount != null) {

            rubricAccountRepository.delete(rubricAccount);

            rubricAccount = rubricAccountRepository.findById(id);

            if (rubricAccount != null) {
                map.put("erro", ErrorMessages.ERROR_OPERATION.toString());
            } else {
                map.put("resultado", "Exclusão realizada com sucesso.");
            }
        } else {
            map.put("error", "Conta não encontrada.");
        }

        return map;
    }


}
