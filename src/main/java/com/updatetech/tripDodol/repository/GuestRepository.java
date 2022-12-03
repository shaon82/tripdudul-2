package com.updatetech.tripDodol.repository;

import com.updatetech.tripDodol.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
}
