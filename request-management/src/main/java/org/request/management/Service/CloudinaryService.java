package org.request.management.Service;



import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.request.management.Entity.UserRegistration;
import org.request.management.Entity.UserRequest;
import org.request.management.Repository.UserRegistrationRepository;
import org.request.management.Repository.UserRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class CloudinaryService {
	 @Autowired
	 private UserRegistrationRepository userRegistrationRepository;
	 
	 private UserRequest userRequest;
	 
	 

	    @Autowired
	    private UserRequestRepository userRequestRepository;
	 
    private final Cloudinary cloudinary;

    public CloudinaryService() {
        // Initialize Cloudinary with your credentials
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
        		 "cloud_name", "dutf5p5eo", // Update this with your correct cloud name
                 "api_key", "769745452764979",
                 "api_secret", "OLxpk-C9ZQiQ8gDf5UVoY6WIjIs"));
    }

    public String uploadFile(MultipartFile file) {
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("folder", "Blood_Request_Document") );
            return uploadResult.get("url").toString(); // return the URL of the uploaded file
        } catch (IOException e) {
            throw new RuntimeException("Error uploading file to Cloudinary", e);
        }
    }
    
    //get all user 
    public UserRegistration findUserByUserId(String userId) {
        return userRegistrationRepository.findByUserId(userId); // Implement this method in your repository
    }
    
   
}

