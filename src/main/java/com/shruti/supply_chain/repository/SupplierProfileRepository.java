package com.shruti.supply_chain.repository;

import com.shruti.supply_chain.model.SupplierProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplierProfileRepository extends JpaRepository<SupplierProfile, Long> {

    Optional<SupplierProfile> findByUserId(Long userId);

}