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
public class LoginController {

    @GetMapping("/login")
    String atsakymas(
            Model model
    ) {
        String error ="error";
        model.addAttribute("registrationForm", new Users());
        model.addAttribute("loginForm", new Users());
        model.addAttribute("error","");
        return "login";
    }


    @PostMapping("/login")
    public String postAtsakymas(
            @ModelAttribute("loginForm") Users user,
            BindingResult result,
            Model model) {
        String returnPage;
        Duombaze db = new Duombaze();
        if (db.loginCheck(user)) {

            model.addAttribute("list", db.getAllUserRecords());
            model.addAttribute("userRecordForm", new UserRecords());
            model.addAttribute("registrationForm", new Users());
            returnPage="/maintable";
        } else {
            model.addAttribute("loginForm",new Users());
            model.addAttribute("error","Blogai ivesti duomenys arba toks naudotojas neegzistuoja!!!");
            returnPage = "/login";
        }

        return returnPage;
    }

}

