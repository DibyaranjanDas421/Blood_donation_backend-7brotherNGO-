package org.donor.management.Repository;


import java.util.List;

import org.donor.management.Entity.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRequestRepository extends JpaRepository<UserRequest, Long> {
	
	
	 @Query("SELECT ur FROM UserRequest ur WHERE ur.receiver.id = :receiverId")
	    UserRequest findRequestsByReceiverId(@Param("receiverId") Long receiverId);
}
