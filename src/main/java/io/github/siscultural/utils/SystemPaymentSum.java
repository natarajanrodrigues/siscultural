package io.github.siscultural.utils;

import io.github.siscultural.repositories.SystemPaymentRepository;
import io.github.siscultural.services.SystemPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

/**
 * Created by natarajan on 11/01/17.
 */
@Component
public class SystemPaymentSum {

    private static SystemPaymentService systemPaymentService;

    @Autowired
    private SystemPaymentService tSystemPaymentService;

    @PostConstruct
    public void init() {
        SystemPaymentSum.systemPaymentService = tSystemPaymentService;
    }


    public static BigDecimal totalFornecedor(String cpfCnpj) {

        return systemPaymentService.totalFornecedor(cpfCnpj);

    }
}
