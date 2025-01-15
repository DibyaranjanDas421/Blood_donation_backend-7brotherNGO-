package org.request.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class RequestManagement 
{
	  public static void main(String[] args) {
	        SpringApplication.run(RequestManagement.class, args);
	        System.out.println("RequestManagementServiceApplication");
	    }
}
