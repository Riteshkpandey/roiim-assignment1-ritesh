package com.cardIntegration.roiim.repository;

import com.cardIntegration.roiim.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Long> {
    Customer findByEmail(String emailId);
}
