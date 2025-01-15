package com.user.management.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.management.Dto.DonorResponseDto;
import com.user.management.Dto.UserRegistrationDto;
import com.user.management.Entity.UserRegistration;
import com.user.management.Repository.UserRepository;

@Service
public class UserService {
    @Autowired
	private UserRepository userRepository;
	
    
    //user registration
	public String registerUser(UserRegistrationDto userRegistrationDto) {
		
		  Optional<UserRegistration> existingUser = userRepository.findByEmailId(userRegistrationDto.getEmailId());

	        // If the user exists with the same email, return an error message
	        if (existingUser.isPresent()) {
	            return "Email already exists. Please use a different email.";
	        }
		UserRegistration saveUser = UserRegistration.builder()
                .fullName(userRegistrationDto.getFullName())
                .bloodGroup(userRegistrationDto.getBloodGroup())
                .mobileNumber(userRegistrationDto.getMobileNumber())
                .country(userRegistrationDto.getCountry())
                .state(userRegistrationDto.getState())
                .district(userRegistrationDto.getDistrict())
                .city(userRegistrationDto.getCity())
                .emailId(userRegistrationDto.getEmailId())
                .userId(userRegistrationDto.getUserId())
                .password(userRegistrationDto.getPassword())
                .availabilityToDonateBlood(userRegistrationDto.isAvailabilityToDonateBlood()) // Correct usage
                .build();

        userRepository.save(saveUser);
        
        
        
        return "ThankYou"+" "+userRegistrationDto.getFullName()+"  "+"Your Registration is successfull";
	}
	
	
	//user login
	
	  // This method checks if the username and password match (using plain text password)
	public boolean authenticateUser(String userId, String password) {
	    Optional<UserRegistration> userOptional = userRepository.findByUserId(userId);

	    if (userOptional.isPresent()) {
	        UserRegistration user = userOptional.get();
	        
	        // Direct password match comparison (without encryption)
	        if (password.equals(user.getPassword())) {
	            return true;
	        }
	    }
	    return false;
	}

	
	
	
	
	
	
	//foundDonorBased on city
	public List<DonorResponseDto> foundDonor(String city, String bloodGroup) {
	    // Query using 'city' instead of 'district'
	    List<UserRegistration> users = userRepository.findByCityAndBloodGroupAndAvailabilityToDonateBlood(city.trim(), bloodGroup.trim(), true);
	    
	    System.out.println(users);  // This will print the user list, verify if it's returning the correct result

	    // Handle the case when no users are found
	    if (users == null || users.isEmpty()) {
	        return Collections.emptyList(); // Return an empty list
	    }

	    // Map users to DonorResponseDto
	    return users.stream()
	            .map(user -> new DonorResponseDto(
	                    user.getId(),
	                    user.getFullName(),
	                    user.getBloodGroup(),
	                    user.getMobileNumber(),
	                    user.getCountry(),
	                    user.getState(),
	                    user.getDistrict(),
	                    user.getCity(),
	                    user.getEmailId(),
	                    user.isAvailabilityToDonateBlood()))
	            .collect(Collectors.toList());
	}

	
	//find by userid
	
	  public UserRegistration getUserByUserId(String userId) {
	        Optional<UserRegistration> userOptional = userRepository.findByUserId(userId);
	        return userOptional.orElse(null);  // Return the user if found, otherwise null
	    }
	
	
	
}
