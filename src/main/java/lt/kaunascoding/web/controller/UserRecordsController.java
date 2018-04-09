package lt.kaunascoding.web.controller;

import lt.kaunascoding.web.model.Duombaze;
import lt.kaunascoding.web.model.tables.UserRecords;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

@Controller
public class StudentsController {

    @GetMapping("/students")
    String atsakymas(
            Model model
    ) {
        Duombaze db = new Duombaze();
        model.addAttribute("studentForm", new UserRecords());
        model.addAttribute("list", db.getAllStudents());
        return "students";
    }

    @PostMapping("/students")
    public String postAtsakymas(
            @ModelAttribute("studentForm") UserRecords userRecords,
            BindingResult result,
            Model model
    ) {
        Duombaze db = new Duombaze();
        if (!StringUtils.isEmpty(userRecords.getName()) && !StringUtils.isEmpty(userRecords.getSurname())) {
            db.insertStudent(userRecords.getName(), userRecords.getSurname(), userRecords.getPhone(), userRecords.getEmail());
        }
        model.addAttribute("list", db.getAllStudents());
        return "students";
    }

}
