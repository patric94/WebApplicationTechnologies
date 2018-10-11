package com.ted.getajob.repository;

import com.ted.getajob.model.Authority;
import com.ted.getajob.model.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByName(AuthorityName name);
}
