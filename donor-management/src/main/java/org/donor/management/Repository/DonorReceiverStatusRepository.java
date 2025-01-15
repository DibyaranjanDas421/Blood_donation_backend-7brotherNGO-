package org.donor.management.Repository;

import java.util.List;
import java.util.Optional;

import org.donor.management.Entity.DonorReceiverStatus;
import org.donor.management.Entity.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorReceiverStatusRepository extends JpaRepository<DonorReceiverStatus, Long> {
	 @Query("SELECT dr FROM DonorReceiverStatus dr WHERE dr.donor.id = :donorId AND dr.status = 'PENDING'")
	    List<DonorReceiverStatus> findPendingRequestsForDonor(@Param("donorId") Long donorId);
	 Optional<DonorReceiverStatus> findByIdAndDonorId(Long requestId, Long donorId);
	
}