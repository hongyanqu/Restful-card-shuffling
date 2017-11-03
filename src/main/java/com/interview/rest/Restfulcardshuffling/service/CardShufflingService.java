package com.interview.rest.Restfulcardshuffling.service;

import java.util.List;

import com.interview.rest.Restfulcardshuffling.entity.Deck;

public interface CardShufflingService {
	public List<Deck> findAll();
	public Deck findDeckById(long id);
	public void deleteById(long id);
	public void save(Deck deck);
	public void shuffle(long id);
	
}
