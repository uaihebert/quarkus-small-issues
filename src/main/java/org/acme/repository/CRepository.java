package org.acme.repository;

import org.acme.model.A;
import org.acme.model.C;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CRepository extends JpaRepository<C, String> {
}
