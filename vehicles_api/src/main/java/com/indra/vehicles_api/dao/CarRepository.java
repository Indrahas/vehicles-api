package com.indra.vehicles_api.dao;

import com.indra.vehicles_api.model.car.Car;
import com.indra.vehicles_api.model.manufacturer.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CarRepository extends JpaRepository<Car, Integer> {

    @Repository
    interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {

    }
}
