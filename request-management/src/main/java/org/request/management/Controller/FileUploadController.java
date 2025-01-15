package org.request.management.Controller;

import org.request.management.Dto.ResponseMessage;
import org.request.management.Entity.UserRequest;
import org.request.management.Entity.UserRegistration;
import org.request.management.Repository.UserRequestRepository;
import org.request.management.Repository.UserRegistrationRepository;
import org.request.management.Service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/user-requests")
@Transactional
public class FileUploadController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private UserRequestRepository userRequestRepository;

    @Autowired
    private UserRegistrationRepository userRegistrationRepository; // Use the repository to fetch user

    // Upload a file and save UserRequest
    @PostMapping("/upload-and-save")
    public ResponseEntity<ResponseMessage> uploadAndSaveFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("userId") String userId, // Add userId parameter
            @RequestParam("fullName") String fullName,
            @RequestParam("bloodGroup") String bloodGroup,
            @RequestParam("mobileNumber") String mobileNumber,
            @RequestParam("country") String country,
            @RequestParam("state") String state,
            @RequestParam("district") String district,
            @RequestParam("city") String city,
            @RequestParam("hospitalName") String hospitalName
    ) {

        // Validate file type
        List<String> allowedContentTypes = Arrays.asList("image/jpeg", "image/png", "application/pdf");

        if (!allowedContentTypes.contains(file.getContentType())) {
            throw new IllegalArgumentException("Only images (JPEG, PNG) and PDFs are allowed");
        }

        // Upload file to Cloudinary
        String fileUrl = cloudinaryService.uploadFile(file);

        // Fetch the UserRegistration by userId from the repository
        UserRegistration userRegistration = userRegistrationRepository.findByUserId(userId);

        // Check if the user is found
        if (userRegistration == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseMessage("User not found", ""));
        }

        // Create a new UserRequest and set the values
        UserRequest userRequest = new UserRequest();
        userRequest.setFullName(fullName);
        userRequest.setBloodGroup(bloodGroup);
        userRequest.setMobileNumber(mobileNumber);
        userRequest.setCountry(country);
        userRequest.setHospitalName(hospitalName);
        userRequest.setState(state);
        userRequest.setDistrict(district);
        userRequest.setCity(city);
        userRequest.setFileUrl(fileUrl);
        userRequest.setReceiver(userRegistration); // Set the userRegistration reference
        System.out.println("HospitalName+"+userRequest.getHospitalName());

        // Save the UserRequest entity in the database
        userRequestRepository.save(userRequest);

        // Return a response message
        ResponseMessage responseMessage = new ResponseMessage("File uploaded successfully.", fileUrl);
        responseMessage.setId(userRequest.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }
    
   
}
