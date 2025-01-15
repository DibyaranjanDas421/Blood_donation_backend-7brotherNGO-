package org.donor.management.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class UserRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false) // Foreign key
    private UserRegistration receiver; // This should be the entire entity reference

    private String bloodGroup;
    private String city;
    private String country;
    private String district;
    private String fileUrl;
    private String fullName;
    private String mobileNumber;
    private String state;
    private String hospitalName;
     
    public String getHospitalName() {
        return hospitalName;
    }

}
