package com.example.villion_product_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @GetMapping("/getGetLibraryWithProduct/{userId}")
    String getLibraryWithProduct(@PathVariable Long userId);

}
