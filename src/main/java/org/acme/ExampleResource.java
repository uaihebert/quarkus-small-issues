package org.acme;

import org.acme.model.A;
import org.acme.model.B;
import org.acme.model.C;
import org.acme.repository.ARepository;
import org.acme.repository.BRepository;
import org.acme.repository.CRepository;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;


@RestController
@RequestMapping("/hello")
public class ExampleResource {

    @Inject
    EntityManager entityManager;

    @GetMapping
    public String hello() {
        // service.query();
        return "hello ";
    }
}