/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import io.github.siscultural.entities.*;
import io.github.siscultural.enums.ErrorMessages;
import io.github.siscultural.repositories.AccomplishmentRepository;
import io.github.siscultural.repositories.PaymentProposalRepository;
import io.github.siscultural.repositories.PresentationRepository;
import io.github.siscultural.repositories.ProgramRepository;
import io.github.siscultural.services.*;
import io.github.siscultural.utils.JsonView;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
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
public class ContractController {

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

    @GetMapping(value = "/contratos")
    public ModelAndView contratos(SessionStatus sessionStatus) {

        ModelAndView mav = new ModelAndView("contratos");
        sessionStatus.setComplete();
        List<Contract> contracts = contractService.findAll();

        mav.addObject("contracts", contracts);

        return mav;

    }

    @RequestMapping(value = "/contrato_autoadd", method=RequestMethod.GET)
    public ModelAndView contratoAutoadd(@RequestParam("id") String id, Contract contract) {
        ModelAndView modelAndView = new ModelAndView("contratos/contrato_add");

        modelAndView.addObject("contract", new Contract());
        modelAndView.addObject("programs", programRepository.findAll());
        modelAndView.addObject("presentations", presentationRepository.findAll());
        modelAndView.addObject("presentationChoosed", presentationRepository.findById(Long.parseLong(id)));
        modelAndView.addObject("specialEvents", specialEventService.findAll());

        return modelAndView;
    }

    @RequestMapping(value = "/contrato_add", method=RequestMethod.GET)
    public ModelAndView contratoAdd(Contract contract) {
        ModelAndView modelAndView = new ModelAndView("contratos/contrato_add");

        modelAndView.addObject("contract", contract);
        modelAndView.addObject("programs", programRepository.findAll());
        modelAndView.addObject("presentations", presentationRepository.findAll());
        modelAndView.addObject("specialEvents", specialEventService.findAll());

        return modelAndView;
    }

    @RequestMapping(value="/contrato_add", method=RequestMethod.POST)
    public ModelAndView cadastrarNovoContrato(@Validated @ModelAttribute ("contract") Contract contract, BindingResult result,
                                            RedirectAttributes redirectAttributes, Model model, SessionStatus sessionStatus) {

        String presentation = contract.getPresentation().getName();
        String program = contract.getProgram().getName();
        String specialEvent = contract.getSpecialEvent().getName();

        model.addAttribute("presentation", presentation);
        model.addAttribute("program", program);
        model.addAttribute("programs", programRepository.findAll());
        model.addAttribute("presentations", presentationRepository.findAll());
        model.addAttribute("specialEvents", specialEventService.findAll());

        ModelAndView modelAndView = new ModelAndView("contratos/contrato_add");

        if (result.hasErrors()) {

            return modelAndView;

        } else {


            Contract contractSaved = contractService.save(contract);

            if (contractSaved != null) {

//                sessionStatus.setComplete();
//                modelAndView = new ModelAndView("redirect:/contratos");
//                modelAndView.addObject("contract", contract);
                modelAndView = new ModelAndView("redirect:/contrato_edit?id=" + contract.getId());

            }

        }

        return modelAndView;
    }


//    @RequestMapping(value = "/contrato_edit", method=RequestMethod.GET)
//    public ModelAndView contratoEdit(@RequestParam("id") String id) {
//        Contract contract = contractService.findById(Long.parseLong(id));
//
//        ModelAndView modelAndView = new ModelAndView("contratos/contrato_add");
////        ModelAndView modelAndView = new ModelAndView("redirect:/contrato_add");
//        modelAndView.addObject("contract", contract);
//        return modelAndView;
//    }



//    @RequestMapping(value = "/contrato_edit", method=RequestMethod.GET)
//    public ModelAndView contratoEdit(@RequestParam("id") String id, SessionStatus sessionStatus) {
//
//        Contract contract = contractService.findById(Long.parseLong(id));
//
//        ModelAndView modelAndView = new ModelAndView("contratos/contrato_edit");
//        modelAndView.addObject("programs", programRepository.findAll());
//        modelAndView.addObject("presentations", presentationRepository.findAll());
////        modelAndView.addObject("accomplishments", accomplishmentRepository.findByContract(contract));
////        modelAndView.addObject("accomplishments", contract.getAccomplishments());
//        modelAndView.addObject("localities", localityService.findAll());
//        modelAndView.addObject("providers", providerService.getAllProviders());
//        modelAndView.addObject("accounts", rubricAccountService.getRubricAccounts(contract.getProgram()));
//        modelAndView.addObject("proposals", paymentProposalRepository.findByContract(contract));
//        modelAndView.addObject("specialEvents", specialEventService.findAll());
//
//        modelAndView.addObject("contract", contract);
//
//
//
//        return modelAndView;
//    }
//
//
    @PostMapping(value = "/contrato/delete")
    @ResponseBody
    public ModelAndView deteleContrato(@ModelAttribute("committe") Committe committe, @RequestParam("id") String id) {

        Map<String, String> map = new HashMap<>();
        map.clear();

        Contract contract = contractService.findById(Long.parseLong(id));
        if (committe != null)
            committe.removeContract(contract);


        return JsonView.returnJsonFromMap(contractService.delete(contract));

    }
//
//
//
//
//    @RequestMapping(value = "/realizacao/add", method=RequestMethod.POST)
//    public ModelAndView realizacaoAdd(@ModelAttribute ("contract") Contract contract, @RequestParam("locality") String locality, @RequestParam("dateTime") String dateTime) {
//
//        Accomplishment accomplishment = new Accomplishment();
//        accomplishment.setLocality(localityService.findById(Long.parseLong(locality)));
//        accomplishment.setContract(contract);
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
//
//        accomplishment.setDateTime(LocalDateTime.parse(dateTime, formatter));
//
//        accomplishmentRepository.save(accomplishment);
//
//
//        ModelAndView modelAndView = new ModelAndView("redirect:/contrato_edit?id=" + contract.getId());
//
//        modelAndView.addObject("contract", contract);
//
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/realizacao/edit", method=RequestMethod.POST)
//    public ModelAndView realizacaoEdit(@RequestParam("id") String id, @RequestParam("locality") String locality, @RequestParam("dateTime") String dateTime) {
//
//        Accomplishment accomplishment = accomplishmentRepository.findById(Long.parseLong(id));
//        accomplishment.setLocality(localityService.findById(Long.parseLong(locality)));
//        Contract contract = accomplishment.getContract();
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
//
//        accomplishment.setDateTime(LocalDateTime.parse(dateTime, formatter));
//
//        accomplishmentRepository.save(accomplishment);
//
//
//        ModelAndView modelAndView = new ModelAndView("redirect:/contrato_edit?id=" + contract.getId());
//        modelAndView.addObject("contract", contract);
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/realizacao/delete", method=RequestMethod.POST)
//    public ModelAndView accomplishmentDelete(@ModelAttribute ("contract") Contract contract, @RequestParam("id") String id) {
//
//        Map<String, String> map = new HashMap<>();
//        map.clear();
//
//        Accomplishment accomplishment = accomplishmentRepository.findById(Long.parseLong(id));
//
//
//        if (accomplishment != null) {
//
//            accomplishmentRepository.delete(accomplishment);
//
//            accomplishment = accomplishmentRepository.findById(Long.parseLong(id));
//
//            if (accomplishment != null) {
//                map.put("erro", ErrorMessages.ERROR_OPERATION.toString());
//            } else {
//                map.put("resultado", "Exclusão realizada com sucesso.");
////                map.put("page_contract", request.getRequestURI().toString());
//            }
//        }
//
//        return JsonView.returnJsonFromMap(map);
//
//    }


}
