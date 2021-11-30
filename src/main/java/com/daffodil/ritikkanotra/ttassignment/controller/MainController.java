package com.daffodil.ritikkanotra.ttassignment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String pingApi() {
        return "Pinged the API root endpoint Successfully";
    }

}
