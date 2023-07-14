package net.javaguides.springboot.repository;

import net.javaguides.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
 Optional<User> findByEmail(String email);
}


//this interface is needed to perform crud operations on User jpa entity
// Client--> controller layer  --> service layer --> repository layer/jpa --> DB