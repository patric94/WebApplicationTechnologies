package com.ted.getajob.repository;

import com.ted.getajob.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<Work, Long> {
}
