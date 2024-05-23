package com.outreach.rest.repository;

import com.outreach.rest.model.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findById(Long id);

    Page<Store> findByNameStartingWithAndCityAndState (String name, String city, String state, Pageable pageable);

}
