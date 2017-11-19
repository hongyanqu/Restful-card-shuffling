package com.interview.rest.Restfulcardshuffling.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.interview.rest.Restfulcardshuffling.entity.Deck;
import com.interview.rest.Restfulcardshuffling.exception.DeckNotFoundException;
import com.interview.rest.Restfulcardshuffling.service.CardShufflingService;


@RestController
public class CardShufflingController {
	
	@Autowired
	CardShufflingService cardShufflingService;
	
	@Autowired
	private MessageSource messageSource;
	
	
	@GetMapping(value = "/decks")
	public List<Deck> getAllDecks() {
		
		return cardShufflingService.findAll();
	}
	
	@GetMapping(value = "/decks/{id}")
	public Resource<Deck> getOneDeck(@PathVariable long id) {
		Deck deck = cardShufflingService.findDeckById(id);
		if(deck == null) throw new DeckNotFoundException("id" + id + ": is not existed.");
		Resource<Deck> resource = new Resource<Deck>(deck);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllDecks());
		resource.add(linkTo.withRel("all-decks"));
		
		return resource;
	}
	
	@PostMapping(value = "/decks")
	public void saveDeck() {
		Deck deck = new Deck();
		cardShufflingService.save(deck);
	}
	
	@PostMapping(value = "/deck/shuffle/{id}")
	public void shuffleDeck(@PathVariable long id) {
		cardShufflingService.shuffle(id);
	}
	
	@DeleteMapping(value = "/deck/{id}")
	public void deleteDeck(@PathVariable long id) {
		cardShufflingService.deleteById(id);
	}
	
	@GetMapping(path="/deck-internationalized")
	public String deckInternationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale){
		return messageSource.getMessage("good.morning.message", null, locale);
	}
}
