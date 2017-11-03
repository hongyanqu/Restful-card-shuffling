package com.interview.rest.Restfulcardshuffling.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Qualifier("ComplexShufflingAlgorithm")
public class ComplexShufflingAlgorithm implements CardShufflingAlgorithmServiceImpl{

	@Override
	public String[] cardShuffling(String[] deck) {
		System.out.println("************** I am ComplexShufflingAlgorithm **************");
		int r = (int)(Math.random() * 100);
		String[] result = new String[52];
		while(r >= 0){
			result = merge(deck, r % 2);
			r--;
			deck = result;
		}
		return deck;
	}
	
    private String[] merge(String[] str, int k) {
    	int index1 = 0;
    	int index2 = 26;
    	if(k == 1){
    		index1 = 26;
    		index2 = 0;
    	}
    	
    	String[] result = new String[str.length];
    	int i = 0;
    	while(i < 52){
    		if(i % 2 == 0){
    			result[i++] = str[index1++];
    		}else{
    			result[i++] = str[index2++];
    		}
    	}
    	return result;
    }

}
