package org.acme.repository;

import org.acme.model.A;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ARepository extends JpaRepository<A, String> {

    @Query("select a from A a join fetch a.b b left join b.c c where c.foo = ?1")
    Page<A> findA(String param, PageRequest page);
}
