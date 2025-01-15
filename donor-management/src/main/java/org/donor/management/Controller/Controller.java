package org.donor.management.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.donor.management.Dto.ResponseRequest;
import org.donor.management.Entity.UserRegistration;
import org.donor.management.Entity.UserRequest;
import org.donor.management.Service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/donors")
public class Controller {
    
	 @Autowired
	private DonorService donorService;
	 
	 

	 @PostMapping("/send")
	 public ResponseEntity<Map<String, String>> sendRequest(@RequestBody Map<String, Long> requestData) {
		 
		 
		 
	     Long receiverId = requestData.get("donorId");
	     Long donorId = requestData.get("receiverId");

	     // Check if donorId and receiverId are the same
	     if (donorId.equals(receiverId)) {
	         Map<String, String> errorResponse = new HashMap<>();
	         errorResponse.put("message", "You cannot send a request to yourself.");
	         return ResponseEntity.badRequest().body(errorResponse);
	     }

	     System.out.println("donorid " + requestData.get("donorId") + " receiverid " + requestData.get("receiverId"));

	     // Call the service to process the request
	     String message = donorService.sendRequest(receiverId, donorId);

	     Map<String, String> successResponse = new HashMap<>();
	     successResponse.put("message", message);  // "Request sent successfully."
	     
	     return ResponseEntity.ok(successResponse);
	 }


	 //pending request api

	  @GetMapping("/donor/{donorId}")
	    public ResponseEntity<List<Map<String, Object>>> viewPendingRequests(@PathVariable Long donorId) {
	        // Fetch pending requests from the service
	        List<Map<String, Object>> requests = donorService.viewPendingRequests(donorId);
	        return ResponseEntity.ok(requests);
	    }
	  
   //respond api
	  @PutMapping("/{donorId}/respond/{requestId}")
	  public ResponseEntity<ResponseRequest> respondToRequest(@PathVariable Long donorId,
	                                                 @PathVariable Long requestId,
	                                                 @RequestBody ResponseRequest responseRequest) {
		  
		  System.out.println("Donor ID: " + donorId);
		    System.out.println("Request ID: " + requestId);
		    System.out.println("Response: " + responseRequest.getResponse());
		    
	      // Validating and processing the donor's response (Accept/Reject)
		    ResponseRequest result = donorService.respondToRequest(donorId, requestId, responseRequest.getResponse());
	      return ResponseEntity.ok(result);
	  }
	
}
