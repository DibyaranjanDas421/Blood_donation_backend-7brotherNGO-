package org.donor.management;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class DonorManagement 
{
	 public static void main(String[] args) {
	        SpringApplication.run(DonorManagement.class, args);
	        System.out.println("DonorManagementServiceApplication");
	    }
}

