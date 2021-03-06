package lt.kaunascoding.web.controller;

import lt.kaunascoding.web.model.Duombaze;
import lt.kaunascoding.web.model.tables.UserRecords;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;

@Controller
public class DebtsController {

    @GetMapping("/debts")
    String atsakymas(
            Model model
    ) {
        Duombaze db = new Duombaze();
        model.addAttribute("userRecordForm", new UserRecords());
        model.addAttribute("list", db.getAllUserRecords());
        return "debts";
    }

    @PostMapping("/debts")
    public String postAtsakymas(
            @ModelAttribute("userRecordForm") UserRecords userRecords,
            BindingResult result,
            Model model
    ) {
        Duombaze db = new Duombaze();
        if (!StringUtils.isEmpty(userRecords.getGroup()) && !StringUtils.isEmpty(userRecords.getSubgroup())) {
            db.insertUserRecord(userRecords.getUserId(), userRecords.getDate(), userRecords.getGroup(), userRecords.getSubgroup(), userRecords.getComment(), userRecords.getSum(), userRecords.getAccount());
        }
        model.addAttribute("list", db.getAllUserRecords());
        return "debts";
    }

}
