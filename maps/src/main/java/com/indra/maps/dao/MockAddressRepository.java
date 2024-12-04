package com.indra.maps.dao;

import com.github.javafaker.Faker;
import com.indra.maps.model.Address;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Implements a mock repository for generating a random address.
 */
@Repository
public class MockAddressRepository {

    /**
     * Gets a random address from the list.
     * @return A new, random address split into street, city, state and zip
     */
    public static Address getRandom() {

        Faker faker = new Faker();

        String streetAndNumber = faker.address().streetAddress();
        String city = faker.address().city();
        String state = faker.address().state();
        String zip = faker.address().zipCode();
        return new Address(streetAndNumber, city, state, zip);
    }

}
