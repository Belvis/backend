package com.poly.sof3021.ph29788.repositories.user;

import com.poly.sof3021.ph29788.entities.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
