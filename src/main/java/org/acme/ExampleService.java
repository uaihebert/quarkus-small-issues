package org.acme;

import org.acme.model.A;
import org.acme.model.B;
import org.acme.model.C;
import org.acme.repository.ARepository;
import org.acme.repository.BRepository;
import org.acme.repository.CRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ExampleService {

    @Autowired
    ARepository aRepository;

    @Autowired
    BRepository bRepository;

    @Autowired
    CRepository cRepository;

    // ISSUE-2
    @PostConstruct
    public void postInit() {
        if (aRepository.count() > 0) {
            return;
        }

        System.out.println("invoke post init");
        var a = new A("A foo");

        aRepository.saveAndFlush(a);

        var b = new B("B foo", a);
        bRepository.saveAndFlush(b);
        a.setB(b);


        var c = new C("C foo", b);
        cRepository.saveAndFlush(c);
        c.setB(b);

        aRepository.saveAndFlush(a);
        cRepository.saveAndFlush(c);
        bRepository.saveAndFlush(b);
    }

    public void query() {
        var page = PageRequest.of(
            0,
            5
        );

        // ISSUE-3
        aRepository.findA("C foo", page);
    }
}
