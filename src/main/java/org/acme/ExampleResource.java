package org.acme;

import org.acme.model.A;
import org.acme.model.B;
import org.acme.model.C;
import org.acme.repository.ARepository;
import org.acme.repository.BRepository;
import org.acme.repository.CRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;


@RestController
@RequestMapping("/hello")
public class ExampleResource {

    // ISSUE-1
    // @PostConstruct
    // public void postInit() {
    // }

    @Autowired
    ExampleService service;

    @GetMapping
    public String hello() {
        // service.query();
        return "hello";
    }
}