package org.donor.management.Repository;




import org.donor.management.Entity.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Long> {
    List<UserRegistration> findByDistrictAndBloodGroupAndAvailabilityToDonateBlood(
            String district, String bloodGroup, Boolean availabilityToDonateBlood);
}
