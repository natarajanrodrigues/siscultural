package io.github.siscultural.services;

import io.github.siscultural.entities.Budget;
import io.github.siscultural.entities.Locality;
import io.github.siscultural.enums.ErrorMessages;
import io.github.siscultural.repositories.LocalityRepository;
import io.github.siscultural.repositories.OrcamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Natarajan Rodrigues on 16/09/16.
 */
@Service
@Transactional
public class BudgetService {

    @Autowired
    private OrcamentoRepository orcamentoRepository;

    @Autowired
    public BudgetService(OrcamentoRepository orcamentoRepository) {
        this.orcamentoRepository = orcamentoRepository;
    }

    public Budget findById(Long id) {
        return orcamentoRepository.findById(id);
    }

    public void setCurrent(Long budgetId) {
        List<Budget> budgetsCurrent = orcamentoRepository.findByCurrent(true);

        for (Budget b : budgetsCurrent) {
            b.setCurrent(false);
            orcamentoRepository.save(b);
        }

        Budget choosenBudget = orcamentoRepository.findById(budgetId);
        choosenBudget.setCurrent(true);
        orcamentoRepository.save(choosenBudget);

    }

    public Budget getCurrentBudget() {
        List<Budget> budgetsCurrent = orcamentoRepository.findByCurrent(true);
        return budgetsCurrent.get(0);
    }

    public List<Budget> findAll () {
        return orcamentoRepository.findAll();
    }

    public Budget save (Budget budget) {

        return orcamentoRepository.save(budget);
    }

    public Map<String, String> delete(Budget budget) {

        Long id = budget.getId();

        Map<String, String> map = new HashMap<>();
        map.clear();

        if (budget != null) {

            orcamentoRepository.delete(budget);

            budget = orcamentoRepository.findById(id);

            if (budget != null) {
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
