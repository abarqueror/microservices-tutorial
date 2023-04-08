package com.tutorial.bikeservice.service;

import com.tutorial.bikeservice.entity.Bike;
import com.tutorial.bikeservice.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {
    @Autowired
    private BikeRepository bikeRepository;

    public List<Bike> getAll() {
        return bikeRepository.findAll();
    }

    public Bike getByBikeId(int bikeId) {
        return bikeRepository.findById(bikeId).orElse(null);
    }

    public List<Bike> getByUserId(int userId) {
        return bikeRepository.getByUserId(userId);
    }

    public Bike save(Bike bike) {
        return bikeRepository.save(bike);
    }


}
