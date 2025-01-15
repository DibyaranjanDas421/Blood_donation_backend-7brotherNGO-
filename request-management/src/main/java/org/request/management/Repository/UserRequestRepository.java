package org.request.management.Repository;


import org.request.management.Entity.UserRegistration;
import org.request.management.Entity.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRequestRepository extends JpaRepository<UserRequest, Long> {
	 
}
