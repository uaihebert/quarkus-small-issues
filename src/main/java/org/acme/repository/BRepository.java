package org.acme.repository;

import org.acme.model.A;
import org.acme.model.B;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BRepository extends JpaRepository<B, String> {
}
