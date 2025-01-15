package org.donor.management.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.donor.management.Dto.ResponseRequest;
import org.donor.management.Entity.DonorReceiverStatus;
import org.donor.management.Entity.UserRegistration;
import org.donor.management.Entity.UserRequest;
import org.donor.management.Repository.DonorReceiverStatusRepository;
import org.donor.management.Repository.UserRegistrationRepository;
import org.donor.management.Repository.UserRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonorService {
   
	  @Autowired
	    private UserRegistrationRepository userRegistrationRepository;
	  
	  @Autowired
	    private UserRequestRepository userRequestRepository;
	  
	  @Autowired
	  DonorReceiverStatusRepository donorReceiverStatusRepository;
	  
	  
	
	  
	  public String sendRequest(Long receiverId, Long donorId) {
		    // Fetch the receiver and donor
		    UserRegistration receiver = userRegistrationRepository.findById(receiverId)
		            .orElseThrow(() -> new IllegalArgumentException("Invalid receiver ID"));

		    UserRegistration donor = userRegistrationRepository.findById(donorId)
		            .orElseThrow(() -> new IllegalArgumentException("Invalid donor ID"));

		    // Validate blood group and district
		    if (!receiver.getBloodGroup().equals(donor.getBloodGroup())) {
		        return "Blood groups do not match.";
		    }

		    // Fetch existing user requests for this receiver
		    UserRequest existingRequests = userRequestRepository.findRequestsByReceiverId(receiverId);

		    // Check if there are existing requests
		    if (existingRequests == null) {
		        return "First fill the blood request before sending a request.";
		    }

		    // Create a new request
		    DonorReceiverStatus request = DonorReceiverStatus.builder()
		            .userRequest(existingRequests)  // Now this is a saved entity
		            .receiver(receiver)
		            .donor(donor)
		            .status("PENDING")
		            .build();

		    // Save the DonorReceiverStatus
		    donorReceiverStatusRepository.save(request);

		    return "Request sent successfully.";
		}
	   
	  public List<Map<String, Object>> viewPendingRequests(Long donorId) {
		    // Fetch pending requests for the donor
		    List<DonorReceiverStatus> pendingRequests = donorReceiverStatusRepository.findPendingRequestsForDonor(donorId);

		    // Transform the result into a list of maps
		    List<Map<String, Object>> response = new ArrayList<>();
		    for (DonorReceiverStatus request : pendingRequests) {
		        Map<String, Object> requestData = new HashMap<>();
		        requestData.put("id", request.getId()); // Add the ID of DonorReceiverStatus
		        requestData.put("receiverId", request.getReceiver().getId());
		        requestData.put("userRequest", request.getUserRequest());
		        requestData.put("hospitalName", request.getUserRequest().getHospitalName());
		        response.add(requestData);
		    }
		    return response;
		}

	   
	   

	   public ResponseRequest respondToRequest(Long donorId, Long requestId, String response) {
	        // Find the pending request by its ID
	        DonorReceiverStatus status = donorReceiverStatusRepository.findById(requestId)
	                .orElseThrow(() -> new IllegalArgumentException("Request not found"));

	        // Ensure the donor is associated with the request
	        if (!status.getDonor().getId().equals(donorId)) {
	            throw new IllegalArgumentException("Donor not associated with this request");
	        }

	        // Update status based on response
	        if ("ACCEPT".equalsIgnoreCase(response)) {
	            status.setStatus("ACCEPTED"); // Update to ACCEPTED
	            // You can add additional logic here for when a request is accepted
	        } else if ("REJECT".equalsIgnoreCase(response)) {
	            status.setStatus("REJECTED"); // Update to REJECTED
	            // You can add additional logic here for when a request is rejected
	        } else {
	            throw new IllegalArgumentException("Invalid response");
	        }

	        // Save the updated status
	        donorReceiverStatusRepository.save(status);
	        
	        ResponseRequest responseRequest=new ResponseRequest();
	        responseRequest.setResponse("Request"+" "+response+" "+" successfully!");

	        return responseRequest;
	    }
	  
}
