package com.online.shopping.general;

import org.springframework.stereotype.Component;

import java.util.Random;


@Component
public class RandomNumber {

    private Random random = new Random();

    public int getRandomNumber() {
        int min = 3;
        int max = 6;
        return random.nextInt(max - min) + min;
    }

}
