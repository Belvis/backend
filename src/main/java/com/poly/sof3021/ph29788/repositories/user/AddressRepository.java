package com.poly.sof3021.ph29788.repositories.user;

import com.poly.sof3021.ph29788.entities.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
