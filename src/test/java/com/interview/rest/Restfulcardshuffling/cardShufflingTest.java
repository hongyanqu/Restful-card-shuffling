package com.interview.rest.Restfulcardshuffling;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.interview.rest.Restfulcardshuffling.controller.CardShufflingController;
import com.interview.rest.Restfulcardshuffling.entity.Deck;
import com.interview.rest.Restfulcardshuffling.service.CardShufflingService;
import com.interview.rest.Restfulcardshuffling.service.impl.CardShufflingServiceImpl;
import org.springframework.http.*;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@WebMvcTest(value = CardShufflingController.class, secure = false)
public class cardShufflingTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	CardShufflingService cardShufflingService;
	
	@InjectMocks
    private CardShufflingController cardShufflingController;
	
	
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(cardShufflingController)
                .build();
    }

	@Test
	public void test_createNewDeck() throws Exception{
	    Deck deck = new Deck(1);
	    doNothing().when(cardShufflingService).save(deck);
	    MvcResult result =  mockMvc.perform(
	            post("/deck")
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .content(asJsonString(deck)))
	            .andExpect(status().isOk()).andReturn();
//	    verify(cardShufflingService, times(1)).save(deck);
//	    verifyNoMoreInteractions(cardShufflingService);

	    assertEquals("",result.getResponse().getContentAsString());
	}
	
	@Test
	public void test_shuffleAnExistingNamedDeck() throws Exception{
	    Deck deck = new Deck();
	    when(cardShufflingService.findDeckById(deck.getId())).thenReturn(deck);
	    doNothing().when(cardShufflingService).shuffle(deck.getId());
	    mockMvc.perform(
	            post("/deck/shuffle/{id}", deck.getId())
	                    .contentType(MediaType.APPLICATION_JSON)
	                    .content(asJsonString(deck)))
	                    .andExpect(status().isOk());
	    verify(cardShufflingService, times(1)).shuffle(deck.getId());
	    verifyNoMoreInteractions(cardShufflingService);
	}
	
	@Test
	public void test_GetAListOfTheCurrentDecks() throws Exception {
		List<Deck> decks = Arrays.asList(
	            new Deck(1),
	            new Deck(2));
		
	    when(cardShufflingService.findAll()).thenReturn(decks);
	    mockMvc.perform(get("/decks"))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	            .andExpect(jsonPath("$[0].id", is(1)))
	            .andExpect(jsonPath("$[1].id", is(2)));
	    verify(cardShufflingService, times(1)).findAll();
	    verifyNoMoreInteractions(cardShufflingService);
	}
	
	@Test
	public void test_GetANamedDeck() throws Exception {
	    Deck deck = new Deck();
	    when(cardShufflingService.findDeckById(1)).thenReturn(deck);
	    mockMvc.perform(get("/deck/{id}", 1))
	            .andExpect(status().isOk())
	            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	    verify(cardShufflingService, times(1)).findDeckById(1);
	    verifyNoMoreInteractions(cardShufflingService);
	}
	
	@Test
	public void test_DeleteANamedDeck() throws Exception{
	    Deck deck = new Deck();
	    doNothing().when(cardShufflingService).deleteById(deck.getId());
	    mockMvc.perform(
	            delete("/deck/{id}", deck.getId()))
	            .andExpect(status().isOk());
	    verify(cardShufflingService, times(1)).deleteById(deck.getId());
	    verifyNoMoreInteractions(cardShufflingService);
	}
	
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
