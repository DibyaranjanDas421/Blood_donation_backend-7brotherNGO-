package org.request.management.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegistration {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id; // Primary key for the entity

	    private String fullName;
	    private String bloodGroup;
	    private String mobileNumber;
	    private String country;
	    private String state;
	    private String district;
	    private String city;
	    private String emailId;
	    private String userId;
	    private String password;
	    private boolean availabilityToDonateBlood;
}