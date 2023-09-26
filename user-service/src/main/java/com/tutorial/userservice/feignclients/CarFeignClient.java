package com.tutorial.userservice.feignclients;

import com.tutorial.userservice.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Ya no haria falta url = "http://localhost:8002" porque registramos con Eureka
@FeignClient(name = "car-service", path="/car")
//@FeignClient(name = "car-service", url = "http://localhost:8002", path="/car")

//No compila separando /car en @RequestMapping de @FeignClient.Se incluye path en @FeignClient
//@FeignClient(name = "car-service", url = "http://localhost:8002")
//@RequestMapping("/car")
public interface CarFeignClient {

    @PostMapping
    Car save(@RequestBody Car car);

    @GetMapping("/byuser/{userId}")
    List<Car> getCars(@PathVariable("userId") int userId);
}
