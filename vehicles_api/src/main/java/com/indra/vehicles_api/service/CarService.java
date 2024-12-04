package com.indra.vehicles_api.service;

import com.indra.vehicles_api.client.maps.MapsClient;
import com.indra.vehicles_api.client.prices.PriceClient;
import com.indra.vehicles_api.dao.CarRepository;

import java.util.List;

import com.indra.vehicles_api.model.car.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implements the car service create, read, update or delete
 * information about vehicles, as well as gather related
 * location and price data when desired.
 */
@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

//    public CarService(CarRepository carRepository) {
//        /**
//         * TODO: Add the Maps and Pricing Web Clients you create
//         *   in `VehiclesApiApplication` as arguments and set them here.
//         */
//        this.carRepository = carRepository;
//    }
    @Autowired
    MapsClient mapsClient;

    @Autowired
    PriceClient priceClient;

    /**
     * Gathers a list of all vehicles
     * @return a list of all vehicles in the CarRepository
     */
    public List<Car> list() {
        List<Car> cars= carRepository.findAll();
        for(int i = 0; i< cars.size(); i++){
            cars.set(i, findById(cars.get(i).getId()));
        }
        return cars;
    }

    /**
     * Gets car information by ID (or throws exception if non-existent)
     * @param id the ID number of the car to gather information on
     * @return the requested car's information, including location and price
     */
    public Car findById(Integer id) {

        Car car = carRepository.findById(id).orElseThrow(CarNotFoundException::new);

        /**
         * TODO: Use the Pricing Web client you create in `VehiclesApiApplication`
         *   to get the price based on the `id` input'
         * TODO: Set the price of the car
         * Note: The car class file uses @transient, meaning you will need to call
         *   the pricing service each time to get the price.
         */

        car.setPrice(priceClient.getPrice(car.getId()));


        /**
         * TODO: Use the Maps Web client you create in `VehiclesApiApplication`
         *   to get the address for the vehicle. You should access the location
         *   from the car object and feed it to the Maps service.
         * TODO: Set the location of the vehicle, including the address information
         * Note: The Location class file also uses @transient for the address,
         * meaning the Maps service needs to be called each time for the address.
         */

        car.setLocation(mapsClient.getAddress(car.getLocation()));


        return car;
    }

    /**
     * Either creates or updates a vehicle, based on prior existence of car
     * @param car A car object, which can be either new or existing
     * @return the new/updated car is stored in the carRepository
     */
    public Car save(Car car) {
        if (car.getId() != null) {
            return carRepository.findById(car.getId())
                    .map(carToBeUpdated -> {
                        carToBeUpdated.setDetails(car.getDetails());
                        carToBeUpdated.setLocation(car.getLocation());
                        return carRepository.save(carToBeUpdated);
                    }).orElseThrow(CarNotFoundException::new);
        }

        return carRepository.save(car);
    }

    /**
     * Deletes a given car by ID
     * @param id the ID number of the car to delete
     */
    public void delete(Integer id) {

        Car car = carRepository.findById(id).orElseThrow(CarNotFoundException::new);

        carRepository.delete(car);

    }
}
