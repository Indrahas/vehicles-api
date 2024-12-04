package com.indra.vehicles_api.client.maps;


import java.util.Objects;

import com.indra.vehicles_api.feign.MapsInterface;
import com.indra.vehicles_api.model.Location;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implements a class to interface with the Maps Client for location data.
 */
@Component
public class MapsClient {

    @Autowired
    MapsInterface mapsInterface;

    private static final Logger log = LoggerFactory.getLogger(MapsClient.class);

//    private final WebClient client;
//    @Autowired
//    ModelMapper mapper;
//
//
//    public MapsClient(
//            ModelMapper mapper) {
//
//        this.mapper = mapper;
//    }

    /**
     * Gets an address from the Maps client, given latitude and longitude.
     * @param location An object containing "lat" and "lon" of location
     * @return An updated location including street, city, state and zip,
     *   or an exception message noting the Maps service is down
     */
    public Location getAddress(Location location) {
        try {
            Address address = mapsInterface.get(location.getLat(), location.getLon());

//            mapper.map(Objects.requireNonNull(address), location);
            location.setAddress(address.getAddress());
            location.setCity(address.getCity());
            location.setZip(address.getZip());
            location.setState(address.getState());

            return location;
        } catch (Exception e) {
            log.warn("Map service is down");
            return location;
        }
    }
}
