package com.interview.rest.Restfulcardshuffling;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import com.interview.rest.Restfulcardshuffling.entity.Deck;
import com.interview.rest.Restfulcardshuffling.service.impl.ComplexShufflingAlgorithm;
import com.interview.rest.Restfulcardshuffling.service.impl.SimpleShufflingAlgorithm;

public class algorithmTest {

	@Test
	public void testComplexShufflingAlgorithm() {
		ComplexShufflingAlgorithm complexShufflingAlgorithm = new ComplexShufflingAlgorithm();
		Deck deck = new Deck();
		System.out.println(Arrays.toString(deck.getDeck()));
		String [] newDeck = complexShufflingAlgorithm.cardShuffling(deck.getDeck());		
		System.out.println(Arrays.toString(newDeck));
	}

	@Test
	public void testSimpleShufflingAlgorithm() {
		SimpleShufflingAlgorithm simpleShufflingAlgorithm = new SimpleShufflingAlgorithm();
		Deck deck = new Deck();
		System.out.println(Arrays.toString(deck.getDeck()));
		String [] newDeck = simpleShufflingAlgorithm.cardShuffling(deck.getDeck());		
		System.out.println(Arrays.toString(newDeck));
	}
}
