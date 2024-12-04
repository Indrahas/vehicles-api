package com.indra.vehicles_api.feign;

import com.indra.vehicles_api.client.prices.Price;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@FeignClient("PRICING-SERVICE")
public interface PriceInterface {

    @GetMapping("/services/price")
    public Price get(@RequestParam Long vehicleId);
}
