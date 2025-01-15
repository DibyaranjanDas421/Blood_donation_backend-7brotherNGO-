package com.user.management.Repository;



import com.user.management.Dto.DonorResponseDto;
import com.user.management.Entity.UserRegistration;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserRegistration, Long> {
    // Custom query methods can be added here if needed

    // Example: Find a user by email ID
	 Optional<UserRegistration> findByEmailId(String emailId);
	 //findDonor by city
	 
	 List<UserRegistration> findByCityAndBloodGroupAndAvailabilityToDonateBlood(String city, String bloodGroup, boolean availabilityToDonateBlood);
	



    //find by userid
    Optional<UserRegistration> findByUserId(String userId);

    // Example: Check if a user exists by mobile number
    boolean existsByMobileNumber(String mobileNumber);
}
