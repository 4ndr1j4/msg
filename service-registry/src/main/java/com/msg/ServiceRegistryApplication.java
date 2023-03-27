package com.msg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaServer
@SpringBootApplication
public class ServiceRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRegistryApplication.class, args);
    }

    // Temporary solution with issue for re-registration of services in spring boot 3
    @RestController
    static class CustomErrorController implements ErrorController {

        private static final String ERROR_MAPPING = "/error";

        @RequestMapping(ERROR_MAPPING)
        public ResponseEntity<Void> error() {
            return ResponseEntity.notFound().build();
        }

    }

}
