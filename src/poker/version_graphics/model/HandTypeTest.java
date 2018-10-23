package poker.version_graphics.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import poker.version_graphics.model.Card.Rank;
import poker.version_graphics.model.Card.Suit;

class HandTypeTest {

	@Test
	void TestStraight() {
		ArrayList<Card> tmpCards = new ArrayList<Card>();
		tmpCards.add(new Card(Suit.Clubs, Rank.Ace));
		tmpCards.add(new Card(Suit.Diamonds, Rank.King));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Queen));
		tmpCards.add(new Card(Suit.Spades, Rank.Jack));
		tmpCards.add(new Card(Suit.Hearts, Rank.Ten));
		
		assertEquals(HandType.evaluateHand(tmpCards), HandType.Straight);
	}
	
	@Test
	void TestOnePair() {
		ArrayList<Card> tmpCards = new ArrayList<Card>();
		tmpCards.add(new Card(Suit.Clubs, Rank.Ace));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Ace));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Eight));
		tmpCards.add(new Card(Suit.Spades, Rank.Jack));
		tmpCards.add(new Card(Suit.Hearts, Rank.Ten));
		
		assertEquals(HandType.evaluateHand(tmpCards), HandType.OnePair);
	}
	
	@Test
	void TestTwoPair() {
		ArrayList<Card> tmpCards = new ArrayList<Card>();
		tmpCards.add(new Card(Suit.Clubs, Rank.Ace));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Ace));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Queen));
		tmpCards.add(new Card(Suit.Spades, Rank.Queen));
		tmpCards.add(new Card(Suit.Hearts, Rank.Ten));
		
		assertEquals(HandType.evaluateHand(tmpCards), HandType.TwoPair);
	}
	
	@Test
	void TestThreeOfAKind() {
		ArrayList<Card> tmpCards = new ArrayList<Card>();
		tmpCards.add(new Card(Suit.Clubs, Rank.Ace));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Ace));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Queen));
		tmpCards.add(new Card(Suit.Spades, Rank.Ace));
		tmpCards.add(new Card(Suit.Hearts, Rank.Ten));
		
		assertEquals(HandType.evaluateHand(tmpCards), HandType.ThreeOfAKind);
	}
	
	@Test
	void TestFourOfAKind() {
		ArrayList<Card> tmpCards = new ArrayList<Card>();
		tmpCards.add(new Card(Suit.Clubs, Rank.Ace));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Ace));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Queen));
		tmpCards.add(new Card(Suit.Spades, Rank.Ace));
		tmpCards.add(new Card(Suit.Hearts, Rank.Ace));
		
		assertEquals(HandType.evaluateHand(tmpCards), HandType.FourOfAKind);
	}
	
	@Test
	void TestFlush() {
		ArrayList<Card> tmpCards = new ArrayList<Card>();
		tmpCards.add(new Card(Suit.Diamonds, Rank.Ace));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Three));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Queen));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Jack));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Eight));
		
		assertEquals(HandType.evaluateHand(tmpCards), HandType.Flush);
	}
	
	@Test
	void TestFullHouse() {
		ArrayList<Card> tmpCards = new ArrayList<Card>();
		tmpCards.add(new Card(Suit.Clubs, Rank.Ace));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Ace));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Queen));
		tmpCards.add(new Card(Suit.Spades, Rank.Queen));
		tmpCards.add(new Card(Suit.Hearts, Rank.Queen));
		
		assertEquals(HandType.evaluateHand(tmpCards), HandType.FullHouse);
	}
	
	@Test
	void TestStraightFlush() {
		ArrayList<Card> tmpCards = new ArrayList<Card>();
		tmpCards.add(new Card(Suit.Clubs, Rank.Three));
		tmpCards.add(new Card(Suit.Clubs, Rank.Four));
		tmpCards.add(new Card(Suit.Clubs, Rank.Five));
		tmpCards.add(new Card(Suit.Clubs, Rank.Six));
		tmpCards.add(new Card(Suit.Clubs, Rank.Seven));
		
		assertEquals(HandType.evaluateHand(tmpCards), HandType.StraightFlush);
	}
	
	@Test
	void TestHighCard() {
		ArrayList<Card> tmpCards = new ArrayList<Card>();
		tmpCards.add(new Card(Suit.Clubs, Rank.Ace));
		tmpCards.add(new Card(Suit.Hearts, Rank.Ten));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Jack));
		tmpCards.add(new Card(Suit.Spades, Rank.Four));
		tmpCards.add(new Card(Suit.Hearts, Rank.Seven));
		
		assertEquals(HandType.evaluateHand(tmpCards), HandType.HighCard);
	}

}
