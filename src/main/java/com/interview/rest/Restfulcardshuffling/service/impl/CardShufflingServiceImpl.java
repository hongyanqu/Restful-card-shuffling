package com.interview.rest.Restfulcardshuffling.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.interview.rest.Restfulcardshuffling.entity.Deck;
import com.interview.rest.Restfulcardshuffling.repository.CardShufflingReposiory;
import com.interview.rest.Restfulcardshuffling.service.CardShufflingService;

@Service
public class CardShufflingServiceImpl implements CardShufflingService{
	
	@Autowired
	CardShufflingReposiory cardShufflingReposiory;
	
	@Autowired
	@Qualifier("ComplexShufflingAlgorithm")
	CardShufflingAlgorithmServiceImpl cardShufflingAlgorithm;

	@Override
	public List<Deck> findAll() {
		
		return cardShufflingReposiory.findAll();
	}

	@Override
	public Deck findDeckById(long id) {
		
		return cardShufflingReposiory.findOne(id);
	}

	@Override
	public void deleteById(long id) {
		
		cardShufflingReposiory.delete(id);
	}

	@Override
	public void save(Deck deck) {
		
		cardShufflingReposiory.save(deck);
	}

	@Override
	public void shuffle(long id) {		
		Deck deck = cardShufflingReposiory.findOne(id);
		deck.setDeck(cardShufflingAlgorithm.cardShuffling(deck.getDeck()));
		cardShufflingReposiory.save(deck);
	}
}
