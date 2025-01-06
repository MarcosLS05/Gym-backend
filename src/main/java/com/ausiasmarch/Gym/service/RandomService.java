package com.ausiasmarch.Gym.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class RandomService {
    
    public int getRandomInt(int min, int max) {
        return (int) (ThreadLocalRandom.current().nextDouble() * (max - min + 1) + min);
    }

}
