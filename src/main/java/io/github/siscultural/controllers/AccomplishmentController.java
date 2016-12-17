/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import io.github.siscultural.entities.Accomplishment;
import io.github.siscultural.entities.Contract;
import io.github.siscultural.enums.ErrorMessages;
import io.github.siscultural.repositories.AccomplishmentRepository;
import io.github.siscultural.repositories.PaymentProposalRepository;
import io.github.siscultural.repositories.PresentationRepository;
import io.github.siscultural.repositories.ProgramRepository;
import io.github.siscultural.services.*;
import io.github.siscultural.utils.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Natarajan
 */
@Controller
@SessionAttributes("contract")
public class AccomplishmentController {

    @Autowired
    ContractService contractService;

    @Autowired
    ProgramRepository programRepository;

    @Autowired
    PresentationRepository presentationRepository;

    @Autowired
    LocalityService localityService;

    @Autowired
    AccomplishmentRepository accomplishmentRepository;

    @Autowired
    ProviderService providerService;

    @Autowired
    RubricAccountService rubricAccountService;

    @Autowired
    PaymentProposalRepository paymentProposalRepository;

    @Autowired
    SpecialEventService specialEventService;

    @Autowired
    PresentationService presentationService;


    @RequestMapping(value = "/contrato_edit", method=RequestMethod.GET)
    public ModelAndView contratoEdit(@RequestParam("id") String id, SessionStatus sessionStatus) {


        Contract contract = contractService.findById(Long.parseLong(id));

        ModelAndView modelAndView = new ModelAndView("contratos/contrato_edit");

        modelAndView.addObject("programs", programRepository.findAll());
        modelAndView.addObject("presentations", presentationRepository.findAll());
//        modelAndView.addObject("accomplishments", accomplishmentRepository.findByContract(contract));
//        modelAndView.addObject("accomplishments", contract.getAccomplishments());
        modelAndView.addObject("localities", localityService.findAll());
        modelAndView.addObject("providers", providerService.getAllProviders());
//        modelAndView.addObject("accounts", rubricAccountService.getRubricAccounts(contract.getProgram()));
        modelAndView.addObject("proposals", paymentProposalRepository.findByContract(contract));
        modelAndView.addObject("specialEvents", specialEventService.findAll());

        modelAndView.addObject("contract", contract);

        return modelAndView;
    }



    @RequestMapping(value = "/realizacao/add", method=RequestMethod.POST)
    public ModelAndView realizacaoAdd(@ModelAttribute ("contract") Contract contract, @RequestParam("locality") String locality,
                                      @RequestParam("dateTime") String dateTime, SessionStatus sessionStatus) {

        Accomplishment accomplishment = new Accomplishment();
        accomplishment.setLocality(localityService.findById(Long.parseLong(locality)));
        accomplishment.setContract(contract);
        contract.addAccomplishments(accomplishment);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        accomplishment.setDateTime(LocalDateTime.parse(dateTime, formatter));

        accomplishmentRepository.saveAndFlush(accomplishment);
        contractService.update(contract);


        ModelAndView modelAndView = new ModelAndView("redirect:/contrato_edit?id=" + contract.getId());

//        modelAndView.addObject("contract", contractService.findById(Long.parseLong(String.valueOf(contract.getId()))));
//        sessionStatus.setComplete();
//        modelAndView.addObject("contract", contractService.findById(contract.getId()));

        return modelAndView;
    }

    @RequestMapping(value = "/realizacao/edit", method=RequestMethod.POST)
    public ModelAndView realizacaoEdit(@RequestParam("id") String id, @RequestParam("locality") String locality, @RequestParam("dateTime") String dateTime) {

        Accomplishment accomplishment = accomplishmentRepository.findById(Long.parseLong(id));
        accomplishment.setLocality(localityService.findById(Long.parseLong(locality)));
        Contract contract = accomplishment.getContract();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        accomplishment.setDateTime(LocalDateTime.parse(dateTime, formatter));

        accomplishmentRepository.save(accomplishment);


        ModelAndView modelAndView = new ModelAndView("redirect:/contrato_edit?id=" + contract.getId());
//        modelAndView.addObject("contract", contract);
        return modelAndView;
    }

    @RequestMapping(value = "/realizacao/delete", method=RequestMethod.POST)
    public ModelAndView accomplishmentDelete(@RequestParam("id") String id) {
//
//        Map<String, String> map = new HashMap<>();
//        map.clear();
//
//        Accomplishment accomplishment = accomplishmentRepository.findById(Long.parseLong(id));
//        contract.removeAccomplishments(accomplishment);
//
//        if (accomplishment != null) {
//
//            accomplishmentRepository.delete(accomplishment);
//
//            accomplishment = accomplishmentRepository.findById(Long.parseLong(id));
//
//
//            if (accomplishment != null) {
//
//                map.put("erro", ErrorMessages.ERROR_OPERATION.toString());
//            } else {
//
//                map.put("resultado", "Exclusão realizada com sucesso.");
////                map.put("page_contract", request.getRequestURI().toString());
//            }
//        }
//
//        return JsonView.returnJsonFromMap(map);

        Accomplishment accomplishment = accomplishmentRepository.findById(Long.parseLong(id));
        Contract contract = accomplishment.getContract();

        contract.removeAccomplishments(accomplishment);
//        System.out.println("id do contrato" + contract.getId());
        accomplishmentRepository.delete(accomplishment);
//        contractService.save(contract);

        ModelAndView modelAndView = new ModelAndView("redirect:/contrato_edit?id=" + contract.getId());
//        modelAndView.addObject("contract", contract);
        return modelAndView;

    }
//
//    @RequestMapping(value = "/audiencia", method=RequestMethod.GET)
//    public ModelAndView accomplishmentAudience(@RequestParam("id") String id, RedirectAttributes redirectAttributes) {
//
//        Accomplishment accomplishment = accomplishmentRepository.findById(Long.parseLong(id));
//        Contract contract = accomplishment.getContract();
//
//        ModelAndView modelAndView = new ModelAndView("presentations/apresentacao_publico");
//
//        modelAndView.addObject("contract", contract);
//        modelAndView.addObject("accomplishment", accomplishment);
//
//        redirectAttributes.addFlashAttribute("accomplishment", accomplishment);
//
//        return modelAndView;
//
//    }
//
//    @RequestMapping(value = "/realizacao/audiencia", method=RequestMethod.POST)
//    public ModelAndView accomplishmentAudience(@ModelAttribute ("accomplishment") Accomplishment accomplishment, @RequestParam("audience") String audience) {
//        Contract contract = accomplishment.getContract();
//
////        Accomplishment accomplishment2 = accomplishmentRepository.findById(accomplishment.getId());
//
//        accomplishment.setAudience(Integer.parseInt(audience));
//        accomplishmentRepository.save(accomplishment);
//        ModelAndView modelAndView = new ModelAndView("redirect:/contrato_edit?id=" + contract.getId());
//        return modelAndView;
//
//    }


}
