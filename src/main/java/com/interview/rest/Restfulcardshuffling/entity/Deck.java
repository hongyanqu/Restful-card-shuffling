package com.interview.rest.Restfulcardshuffling.entity;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;


@Entity
public class Deck {
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	
	@Column( length = 100000 )
    private String[] deck;
	
	@Transient
    private String[] ranks ={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	
	@Transient
    private String[] suits ={"SPADE","HEART","CLUB","DIAMOND"};

    public Deck() {
        deck = new String[52];
        int k = 0;
        for(int i=0;i<suits.length;i++) {
            for(int j=0;j<ranks.length;j++) {
            	deck[k++] = new String(ranks[j] + "-" + suits[i]);
            }
        }
    }

//    public  void shuffle() {
//        ArrayList<String> temp = new ArrayList<String>();
//        while(!deck.isEmpty()) {
//            int loc=(int)(Math.random()*deck.size());
//            temp.add(deck.get(loc));
//            deck.remove(loc);   
//        }
//        deck=temp;
//    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getDeck() {
		return deck;
	}

	public void setDeck(String[] deck) {
		this.deck = deck;
	}     
    
    
}
