package io.github.siscultural.services;

import io.github.siscultural.entities.Locality;
import io.github.siscultural.enums.ErrorMessages;
import io.github.siscultural.repositories.LocalityRepository;
import io.github.siscultural.repositories.SystemPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by susanneferraz on 16/09/16.
 */
@Service
public class SystemPaymentService {

    @Autowired
    private SystemPaymentRepository systemPaymentRepository;

    @Autowired
    public SystemPaymentService(SystemPaymentRepository systemPaymentRepository) {
        this.systemPaymentRepository = systemPaymentRepository;
    }

    public BigDecimal totalFornecedor(String cpfCnpj) {
        BigDecimal result = systemPaymentRepository.sumProvider(cpfCnpj);

        if (result == null)
            return new BigDecimal(0);

        return result;
    }


}
