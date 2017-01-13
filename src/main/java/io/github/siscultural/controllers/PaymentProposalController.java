/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import io.github.siscultural.entities.Budget;
import io.github.siscultural.entities.Contract;
import io.github.siscultural.entities.Locality;
import io.github.siscultural.entities.PaymentProposal;
import io.github.siscultural.repositories.ContractRepository;
import io.github.siscultural.repositories.PaymentProposalRepository;
import io.github.siscultural.services.BudgetService;
import io.github.siscultural.services.LocalityService;
import io.github.siscultural.services.ProviderService;
import io.github.siscultural.services.RubricAccountService;
import io.github.siscultural.utils.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Natarajan
 */
@Controller
@SessionAttributes({"paymentProposal", "contract"})
public class PaymentProposalController {

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    RubricAccountService rubricAccountService;

    @Autowired
    ProviderService providerService;

    @Autowired
    PaymentProposalRepository paymentProposalRepository;

//    @GetMapping(value = "/proposta_pagamento")
//    public ModelAndView localidades (SessionStatus sessionStatus) {
//
//        ModelAndView mav = new ModelAndView("localidades");
//        sessionStatus.setComplete();
//        List<Locality> localities = localityService.findAll();
//
//        mav.addObject("localities", localities);
//
//        return mav;
//
//    }

    @RequestMapping(value = "/proposta_pagamento", method=RequestMethod.GET)
    public ModelAndView propostas(Contract contract, PaymentProposal paymentProposal) {
        ModelAndView modelAndView = new ModelAndView("contratos/proposta_pagamento");
        modelAndView.addObject("paymentProposal", paymentProposal);

        modelAndView.addObject("accounts", rubricAccountService.getRubricAccounts(contract.getProgram()));
        modelAndView.addObject("providers", providerService.getAllProviders());


        return modelAndView;
    }

    @RequestMapping(value="/proposal/add", method=RequestMethod.POST)
    public ModelAndView propostaAdd(@ModelAttribute ("contract") Contract contract, @RequestParam("conta") String conta,
                                      @RequestParam("provider") String provider, @RequestParam("amount") String amount) throws ParseException {

        PaymentProposal paymentProposal = new PaymentProposal();

        paymentProposal.setContract(contract);

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        String pattern = "0,00";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);

//        paymentProposal.setAmount(new BigDecimal(amount, new MathContext(2, RoundingMode.CEILING)));

        paymentProposal.setAmount(new BigDecimal(String.valueOf((BigDecimal) decimalFormat.parse(amount))));
        paymentProposal.setProvider(providerService.findbyId(Long.parseLong(provider)));
        paymentProposal.setRubricAccount(rubricAccountService.findById(Long.parseLong(conta)));

        contract.addPaymentProposal(paymentProposal);

        contractRepository.save(contract);

        ModelAndView modelAndView = new ModelAndView("redirect:/contrato_edit?id=" + contract.getId());

        return modelAndView;
    }

    @RequestMapping(value="/proposal/delete", method=RequestMethod.POST)
    public ModelAndView propostaDelete(@ModelAttribute ("contract") Contract contract, @RequestParam("id") String id) {

        PaymentProposal paymentProposal = paymentProposalRepository.findById(Long.parseLong(id));

        Contract contract2 = paymentProposal.getContract();

        contract2.removePaymentProposal(paymentProposal);

        contractRepository.save(contract2);

        paymentProposalRepository.delete(paymentProposal);

        ModelAndView modelAndView = new ModelAndView("redirect:/contrato_edit?id=" + contract2.getId());

        return modelAndView;
    }



//    @RequestMapping(value="/proposal/add", method=RequestMethod.POST)
//    public ModelAndView localidadeAdd(@Validated @ModelAttribute ("paymentProposal") PaymentProposal paymentProposal, BindingResult result,
//                                            RedirectAttributes redirectAttributes, Model model, SessionStatus sessionStatus) {
//
//        String conta = paymentProposal.getRubricAccount().getRubric().getName();
////        String provider = paymentProposal.get;
//        String amount = paymentProposal.getAmount().toString();
//
//
//        model.addAttribute("conta", conta);
////        model.addAttribute("address", address);
//        model.addAttribute("amoutn", amount);
//
//
//        ModelAndView modelAndView = new ModelAndView("contratos");
//
//        if (result.hasErrors()) {
//
//            return new ModelAndView("/proposta_pagamento");
//
//        } else {
//
//
////            PaymentProposal proposal = pay;
////
////            if (locality2 != null) {
////
////                sessionStatus.setComplete();
////                modelAndView = new ModelAndView("redirect:/localidades");
////
////            }
//
//            System.out.println("aqui salvar");
//
//        }
//
//        return modelAndView;
//    }


//    @RequestMapping(value = "/localidade_edit", method=RequestMethod.GET)
//    public ModelAndView apresentacaoEdit(@RequestParam("id") String id) {
//        Locality locality = localityService.findById(Long.parseLong(id));
//        ModelAndView modelAndView = new ModelAndView("localidades/localidade_add");
//        modelAndView.addObject("locality", locality);
//        return modelAndView;
//    }
//
//
//    @PostMapping(value = "/localidade/delete")
//    @ResponseBody
//    public ModelAndView deteleLocalidade(@RequestParam("id") String id) {
//
//        Map<String, String> map = new HashMap<>();
//        map.clear();
//
//        Locality locality = localityService.findById(Long.parseLong(id));
//
//        return JsonView.returnJsonFromMap(localityService.delete(locality));
//
//    }


}
