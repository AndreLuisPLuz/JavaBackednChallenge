package com.challenge;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.challenge.interfaces.services.CollatzService;
import com.challenge.interfaces.services.ImaginaryExponentialService;
import com.challenge.interfaces.services.PasswordHasherService;
import com.challenge.interfaces.services.ReverseService;
import com.challenge.interfaces.services.TestPasswordService;
import com.challenge.services.DefaultCollatzService;
import com.challenge.services.DefaultImaginaryExponentialService;
import com.challenge.services.DefaultReverseService;
import com.challenge.services.MockPasswordService;
import com.challenge.services.PBKDF2PasswordHasher;

@Configuration
public class DependenciesConfigurator {
    @Bean
    @Scope("singleton")
    protected ReverseService reverseService() {
        return new DefaultReverseService();
    }

    @Bean
    @Scope("singleton")
    protected ImaginaryExponentialService imaginaryExponentialService() {
        return new DefaultImaginaryExponentialService();
    }

    @Bean
    @Scope("singleton")
    protected CollatzService collatzService() {
        return new DefaultCollatzService();
    }

    @Bean
    @Scope("prototype")
    protected PasswordHasherService passwordHasherService() {
        return new PBKDF2PasswordHasher();
    }

    @Bean
    @Scope("singleton")
    protected TestPasswordService testPasswordService() {
        return new MockPasswordService();
    }
}
