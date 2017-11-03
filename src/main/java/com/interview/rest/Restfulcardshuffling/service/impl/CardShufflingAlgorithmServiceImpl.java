package com.interview.rest.Restfulcardshuffling.service.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.interview.rest.Restfulcardshuffling.entity.Deck;

@Service
public interface CardShufflingAlgorithmServiceImpl {
	public String[] cardShuffling(String[] deck);
}
