
package org.request.management.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dutf5p5eo", // Update this with your correct cloud name
            "api_key", "769745452764979",
            "api_secret", "OLxpk-C9ZQiQ8gDf5UVoY6WIjIs"
        ));
    }
}


