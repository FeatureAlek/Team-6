package lv.acnbootcamp.team6.banking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/")

    public String index() {
       return "index";
    }
}
