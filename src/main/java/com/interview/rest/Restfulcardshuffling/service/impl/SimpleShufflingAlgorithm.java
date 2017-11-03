package com.interview.rest.Restfulcardshuffling.service.impl;

import java.util.ArrayList;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Qualifier("SimpleShufflingAlgorithm")
public class SimpleShufflingAlgorithm implements CardShufflingAlgorithmServiceImpl{

    
	@Override
	public String[] cardShuffling(String[] deck) {
		System.out.println("************** I am SimpleShufflingAlgorithm **************");
        String[] rand = new String[deck.length];
        for (int i = 0; i < deck.length; i++){
            int r = (int) (Math.random() * (i+1));
            rand[i] = rand[r];
            rand[r] = deck[i];
        }
        return rand;
	}

}
