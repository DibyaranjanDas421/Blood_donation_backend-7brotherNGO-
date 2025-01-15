package org.donor.management.Entity;

import java.util.List;

import jakarta.persistence.Entity;
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
public class DonorReceiverStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_request_id", nullable = false) // Maps to UserRequest
    private UserRequest userRequest;

    @ManyToOne
    @JoinColumn(name = "donor_id") // Maps to UserRegistration for the donor
    private UserRegistration donor;

    @ManyToOne
    @JoinColumn(name = "receiver_id") // Maps to UserRegistration for the receiver
    private UserRegistration receiver;

    private String status; // e.g., PENDING, ACCEPTED, REJECTED

    private java.time.LocalDateTime updatedAt;
}
