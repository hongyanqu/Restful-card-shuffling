package com.interview.rest.Restfulcardshuffling.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.interview.rest.Restfulcardshuffling.entity.Deck;
import com.interview.rest.Restfulcardshuffling.service.CardShufflingService;


@RestController
public class CardShufflingController {
	
	@Autowired
	CardShufflingService cardShufflingService;
	
	
	@RequestMapping(value = "/decks", method = RequestMethod.GET)
	public List<Deck> getAllDecks() {
		
		return cardShufflingService.findAll();
	}
	
	@RequestMapping(value = "/deck/{id}", method = RequestMethod.GET)
	public Deck getOneDeck(@PathVariable long id) {
		
		return cardShufflingService.findDeckById(id);
	}
	
	@RequestMapping(value = "/deck", method = RequestMethod.POST)
	public void saveDeck() {
		Deck deck = new Deck();
		cardShufflingService.save(deck);
	}
	
	@RequestMapping(value = "/deck/shuffle/{id}", method = RequestMethod.POST)
	public void shuffleDeck(@PathVariable long id) {
		cardShufflingService.shuffle(id);
	}
	
	@RequestMapping(value = "/deck/{id}", method = RequestMethod.DELETE)
	public void deleteDeck(@PathVariable long id) {
		cardShufflingService.deleteById(id);
	}
}
