package com.outreach.rest.repository;

import com.outreach.rest.model.NPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NPORepository extends JpaRepository<NPO, Long> {
    @Override
    Optional<NPO> findById(Long id);

    Page<NPO> findByNameStartingWithAndCityAndState (String name, String city, String state, Pageable pageable);
}
