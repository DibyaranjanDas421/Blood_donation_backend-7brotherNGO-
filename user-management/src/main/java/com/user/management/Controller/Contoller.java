package com.user.management.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.net.HttpHeaders;
import com.user.management.Dto.DonorResponseDto;
import com.user.management.Dto.LoginResponseDTO;
import com.user.management.Dto.UserRegistrationDto;
import com.user.management.Entity.UserRegistration;
import com.user.management.Service.UserService;

@RestController
@RequestMapping("/users")
public class Contoller {
     

	@Autowired
	private UserService userService;
	
	
	 //user registration  
	@PostMapping("/register")
	public ResponseEntity<String> createCitizen(@RequestBody UserRegistrationDto userRegistrationDto) {
	    try {
	        
	        String result = userService.registerUser(userRegistrationDto);
	        
	 
	        System.out.println("Registration result: " + result);
	        
	       
	        return ResponseEntity.ok()
	                             .header(HttpHeaders.CONTENT_TYPE, "text/plain")
	                             .body(result);
	    } catch (Exception e) {
	      
	        e.printStackTrace();

	        
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Registration failed: " + e.getMessage());
	    }
	}

//	@PostMapping("/login")
//	public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
//	    try {
//	        String userId = loginRequest.get("userId").trim();
//	        String password = loginRequest.get("password").trim();
//
//	        // Authenticate the user using your user service
//	        boolean isAuthenticated = userService.authenticateUser(userId, password);
//
//	        if (isAuthenticated) {
//	            // Create response DTO with userId and message
//	            LoginResponseDTO responseDTO = new LoginResponseDTO(userId, "Login successful");
//	            
//	            return ResponseEntity.ok(responseDTO);
//	        } else {
//	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//	                                 .body(Map.of("message", "Invalid username or password"));
//	        }
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//	                             .body(Map.of("message", "Login failed: " + e.getMessage()));
//	    }
//	}


	   
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
	    try {
	        String userId = loginRequest.get("userId").trim();
	        String password = loginRequest.get("password").trim();

	        // Authenticate the user using your user service
	        boolean isAuthenticated = userService.authenticateUser(userId, password);

	        if (isAuthenticated) {
	            // Fetch the user entity to retrieve the ID
	            UserRegistration user = userService.getUserByUserId(userId);

	            if (user == null) {
	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                                     .body(Map.of("message", "Invalid username or password"));
	            }

	            // Create response DTO with userId, id, and message
	            LoginResponseDTO responseDTO = new LoginResponseDTO(userId, user.getId(), "Login successful");

	            return ResponseEntity.ok(responseDTO);
	        } else {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                                 .body(Map.of("message", "Invalid username or password"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body(Map.of("message", "Login failed: " + e.getMessage()));
	    }
	}

	
	
	  
      //Found donor based on city
	  @GetMapping("/foundDonor/{city}/{bloodGroup}")
	  public ResponseEntity<?> foundDonor(@PathVariable String city,@PathVariable String bloodGroup) {
		  
	      List<DonorResponseDto> donors = userService.foundDonor(city.trim(), bloodGroup.trim());
	      
	      System.out.print(city+""+bloodGroup);
	      
	      System.out.println(donors);
	      if (donors.isEmpty()) {
	          // Return 404 Not Found with a custom message
	          Map<String, String> response = new HashMap<>();
	          response.put("message", "No donors found in the specified city: " + city);

	          return ResponseEntity
	                  .status(HttpStatus.NOT_FOUND)
	                  .body(response);
	      }

	      // Return the list of donors with a 200 OK status
	      return ResponseEntity.ok(donors);
	  }
	
}
