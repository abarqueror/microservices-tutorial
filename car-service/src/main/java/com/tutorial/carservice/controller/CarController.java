package com.tutorial.carservice.controller;

import com.tutorial.carservice.entity.Car;
import com.tutorial.carservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAll() {
        List<Car> allUsers = carService.getAll();
        if (allUsers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable("id") int id) {

        Car userById = carService.geById(id);

        if (userById == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userById);
    }

    @PostMapping()
    public ResponseEntity<Car> save(@RequestBody Car user) {

        Car newUser = carService.save(user);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/byuser/{userId}")
    public ResponseEntity<List<Car>> getByUserId(@PathVariable("userId") int userId) {
        List<Car> allUsers = carService.byUserId(userId);
//        if (allUsers.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
        return ResponseEntity.ok(allUsers);
    }

}
