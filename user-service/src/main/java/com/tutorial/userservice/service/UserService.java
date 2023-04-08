package com.tutorial.userservice.service;

import com.tutorial.userservice.entity.User_;
import com.tutorial.userservice.feignclients.BikeFeignClient;
import com.tutorial.userservice.feignclients.CarFeignClient;
import com.tutorial.userservice.model.Bike;
import com.tutorial.userservice.model.Car;
import com.tutorial.userservice.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;


    @Autowired
    CarFeignClient carFeignClient;

    @Autowired
    BikeFeignClient bikeFeignClient;

    public List<User_> getAll() {
        return userRepository.findAll();
    }

    public User_ getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User_ save(User_ user) {
        return userRepository.save(user);
    }

    //restTemplate para acceder a los otros microservicios
    public List<Car> getCars(int userId) {
        return restTemplate.getForObject("http://localhost:8002/car/byuser/" + userId, List.class);
    }

    public List<Bike> getBikes(int userId) {
        return restTemplate.getForObject("http://localhost:8003/bike/byuser/" + userId, List.class);
    }

    public Car saveCar(int userId, Car car) {
        car.setUserId(userId);
        return carFeignClient.save(car);
    }

    public Bike saveBike(int userId, Bike bike) {
        bike.setUserId(userId);
        return bikeFeignClient.save(bike);
    }

    public Map<String, Object> getUserAndVehicles(int userId) {
        Map<String, Object> result = new HashMap<>();
        User_ user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            result.put("Mensaje", "no esxiste el usuario");
            return result;
        }
        result.put("User", user);
        List<Car> cars = carFeignClient.getCars(userId);
        if (cars.isEmpty()) {
            result.put("Cars", "ese user no tiene coches");
        } else {
            result.put("Cars", cars);
        }
        List<Bike> bikes = bikeFeignClient.getBikes(userId);
        if (bikes.isEmpty()) {
            result.put("Bikes", "ese user no tiene motos");
        } else {
            result.put("Bikes", bikes);
        }
        return result;
    }
}
