package org.request.management.Repository;

import org.request.management.Entity.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Long> {
    UserRegistration findByUserId(String userId); // Custom query to find by userId
}
