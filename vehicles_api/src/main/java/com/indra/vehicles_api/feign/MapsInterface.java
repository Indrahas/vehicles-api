package com.indra.vehicles_api.feign;

import com.indra.vehicles_api.client.maps.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("MAPS")
public interface MapsInterface {

    @GetMapping("/maps")
    public Address get(@RequestParam Double lat, @RequestParam Double lon);
}
