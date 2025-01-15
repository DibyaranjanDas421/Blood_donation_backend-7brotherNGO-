package com.user.management;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserManagement 
{
    public static void main(String[] args) {
        SpringApplication.run(UserManagement.class, args);
        System.out.println("UserManagementServiceApplication");
    }
}