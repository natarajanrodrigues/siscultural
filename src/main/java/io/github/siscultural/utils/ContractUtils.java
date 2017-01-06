package io.github.siscultural.utils;

import io.github.siscultural.entities.Contract;
import io.github.siscultural.entities.PaymentProposal;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by natarajan on 05/01/17.
 */
public class ContractUtils {

    public static double total(Set<Contract> contractSet) {
//        return contractSet.stream()
//                .map(x -> x.getPaymentProposals())
//                .flatMap(x -> x.stream())
//                .mapToDouble(x -> x.getAmount().doubleValue())
////                .mapToDouble(BigDecimal:)
////                .forEach(x -> System.out.println(x))
//                .sum();

        double total = 0;
        for (Contract c : contractSet) {
            for (PaymentProposal p : c.getPaymentProposals()) {
                total += p.getAmount().doubleValue();
            }
        }

        return total;
    }

    public static double totalPerList(List<Contract> contractList) {

        double total = 0;
        for (Contract c : contractList) {
            for (PaymentProposal p : c.getPaymentProposals()) {
                total += p.getAmount().doubleValue();
            }
        }

        return total;
    }


}
