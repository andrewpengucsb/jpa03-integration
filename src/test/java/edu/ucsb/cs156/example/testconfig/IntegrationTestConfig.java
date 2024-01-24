package edu.ucsb.cs156.example.testconfig;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import edu.ucsb.cs156.example.services.CurrentUserService;
import edu.ucsb.cs156.example.services.CurrentUserServiceImpl;
import edu.ucsb.cs156.example.services.GrantedAuthoritiesService;

@TestConfiguration
public class IntegrationTestConfig {

    @Bean
    public CurrentUserService currentUserService() {
        return new CurrentUserServiceImpl();
    }

    @Bean
    public GrantedAuthoritiesService grantedAuthoritiesService() {
        return new GrantedAuthoritiesService();
    }
}
