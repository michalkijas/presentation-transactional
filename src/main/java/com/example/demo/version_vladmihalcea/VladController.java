package com.example.demo.version_vladmihalcea;

import com.example.demo.sandbox.ReadOnlyFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class VladController {

    private final ReadOnlyFacade readOnlyFacade;


    @PostMapping("/vlad/leads")
    void create() {
        readOnlyFacade.create("test");
    }

    @GetMapping("/vlad/leads")
    void findALl() {
        readOnlyFacade.findAll();
    }

}
