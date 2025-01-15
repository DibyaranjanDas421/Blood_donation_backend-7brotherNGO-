package com.user.management.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonorResponseDto {
	private Long id;                         // Unique identifier
    private String fullName;                 // User's full name
    private String bloodGroup;               // User's blood group
    private String mobileNumber;             // Contact mobile number
    private String country;                  // Country
    private String state;                    // State
    private String district;                 // District
    private String city;                     // City
    private String emailId;                  // Email address
                               // Unique user identifier
    private boolean availabilityToDonateBlood;

}
