package lt.kaunascoding.web.controller;

import lt.kaunascoding.web.model.Duombaze;
import lt.kaunascoding.web.model.tables.UserRecords;
import lt.kaunascoding.web.model.tables.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

@Controller
public class RegistrationController {

    @GetMapping("/registration")
    String atsakymas(
            Model model
    ) {
        String error ="error";
        model.addAttribute("loginForm", new Users());
        model.addAttribute("registrationForm", new Users());
        model.addAttribute("error1","");
        return "registration";
    }


    @PostMapping("/registration")
    public String postAtsakymas(
            @ModelAttribute("registrationForm") Users user,
            BindingResult result,
            Model model) {
        String returnPage;
        Duombaze db = new Duombaze();
        if (db.registrationCheck(user)) {

            model.addAttribute("list", db.getAllUserRecords());
            model.addAttribute("userRecordForm", new UserRecords());
            model.addAttribute("loginForm", new Users());
            db.insertUser();
            returnPage="/login";
        } else {
            model.addAttribute("registrationForm",new Users());
            model.addAttribute("error1","Toks vartotojo vardas negalimas!!!");
            returnPage = "/registration";
        }

        return returnPage;
    }

}
