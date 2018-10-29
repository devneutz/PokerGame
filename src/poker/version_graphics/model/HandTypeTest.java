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
	
	@Test
	void TestTieBreakStraight() {
		ArrayList<Card> tmpCards = new ArrayList<Card>();
		tmpCards.add(new Card(Suit.Clubs, Rank.Ace));
		tmpCards.add(new Card(Suit.Diamonds, Rank.King));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Queen));
		tmpCards.add(new Card(Suit.Spades, Rank.Jack));
		tmpCards.add(new Card(Suit.Hearts, Rank.Ten));
		
		ArrayList<Card> tmpCards2 = new ArrayList<Card>();
		tmpCards2.add(new Card(Suit.Clubs, Rank.Ace));
		tmpCards2.add(new Card(Suit.Diamonds, Rank.King));
		tmpCards2.add(new Card(Suit.Diamonds, Rank.Queen));
		tmpCards2.add(new Card(Suit.Spades, Rank.Jack));
		tmpCards2.add(new Card(Suit.Hearts, Rank.Ten));
		
		assertEquals(HandType.StraightFlush.compareTieBreaker(tmpCards, tmpCards2), 0);
	}
	
	@Test
	void TestTieBreakStraightWinnerOne() {
		ArrayList<Card> tmpCards = new ArrayList<Card>();
		tmpCards.add(new Card(Suit.Clubs, Rank.Ace));
		tmpCards.add(new Card(Suit.Diamonds, Rank.King));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Queen));
		tmpCards.add(new Card(Suit.Spades, Rank.Jack));
		tmpCards.add(new Card(Suit.Hearts, Rank.Ten));
		
		ArrayList<Card> tmpCards2 = new ArrayList<Card>();
		tmpCards2.add(new Card(Suit.Clubs, Rank.Queen));
		tmpCards2.add(new Card(Suit.Diamonds, Rank.Jack));
		tmpCards2.add(new Card(Suit.Diamonds, Rank.Ten));
		tmpCards2.add(new Card(Suit.Spades, Rank.Nine));
		tmpCards2.add(new Card(Suit.Hearts, Rank.Eight));		
		
		assertTrue(HandType.StraightFlush.compareTieBreaker(tmpCards, tmpCards2)> 0);
	}
	
	@Test
	void TestTieBreakFullHouse() {
		ArrayList<Card> tmpCards = new ArrayList<Card>();
		tmpCards.add(new Card(Suit.Clubs, Rank.Ten));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Ten));
		tmpCards.add(new Card(Suit.Hearts, Rank.Ten));
		tmpCards.add(new Card(Suit.Clubs, Rank.Jack));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Jack));
		
		ArrayList<Card> tmpCards2 = new ArrayList<Card>();
		tmpCards2.add(new Card(Suit.Clubs, Rank.Queen));
		tmpCards2.add(new Card(Suit.Diamonds, Rank.Queen));
		tmpCards2.add(new Card(Suit.Hearts, Rank.Queen));
		tmpCards2.add(new Card(Suit.Spades, Rank.Nine));
		tmpCards2.add(new Card(Suit.Hearts, Rank.Nine));		
		
		assertTrue(HandType.FullHouse.compareTieBreaker(tmpCards, tmpCards2)<0);
	}
	
	@Test
	void TestTieBreakFullHouseTwo() {
		ArrayList<Card> tmpCards = new ArrayList<Card>();
		tmpCards.add(new Card(Suit.Clubs, Rank.Ace));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Ace));
		tmpCards.add(new Card(Suit.Hearts, Rank.Ace));
		tmpCards.add(new Card(Suit.Clubs, Rank.Jack));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Jack));
		
		ArrayList<Card> tmpCards2 = new ArrayList<Card>();
		tmpCards2.add(new Card(Suit.Clubs, Rank.Queen));
		tmpCards2.add(new Card(Suit.Diamonds, Rank.Queen));
		tmpCards2.add(new Card(Suit.Hearts, Rank.Queen));
		tmpCards2.add(new Card(Suit.Spades, Rank.Nine));
		tmpCards2.add(new Card(Suit.Hearts, Rank.Nine));		
		
		assertTrue(HandType.FullHouse.compareTieBreaker(tmpCards, tmpCards2)>0);
	}
	
	@Test
	void TestTieBreakOnePairWin() {
		ArrayList<Card> tmpCards = new ArrayList<Card>();
		tmpCards.add(new Card(Suit.Clubs, Rank.Ace));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Ace));
		tmpCards.add(new Card(Suit.Hearts, Rank.Ten));
		tmpCards.add(new Card(Suit.Clubs, Rank.Jack));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Four));
		
		ArrayList<Card> tmpCards2 = new ArrayList<Card>();
		tmpCards2.add(new Card(Suit.Clubs, Rank.Queen));
		tmpCards2.add(new Card(Suit.Diamonds, Rank.Queen));
		tmpCards2.add(new Card(Suit.Hearts, Rank.Jack));
		tmpCards2.add(new Card(Suit.Spades, Rank.Ten));
		tmpCards2.add(new Card(Suit.Hearts, Rank.Three));		
		
		assertTrue(HandType.OnePair.compareTieBreaker(tmpCards, tmpCards2)>0);
	}
	
	@Test
	void TestTieBreakOnePairLoose() {
		ArrayList<Card> tmpCards = new ArrayList<Card>();
		tmpCards.add(new Card(Suit.Clubs, Rank.Ace));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Ace));
		tmpCards.add(new Card(Suit.Hearts, Rank.Ten));
		tmpCards.add(new Card(Suit.Clubs, Rank.Jack));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Four));
		
		ArrayList<Card> tmpCards2 = new ArrayList<Card>();
		tmpCards2.add(new Card(Suit.Clubs, Rank.Ace));
		tmpCards2.add(new Card(Suit.Diamonds, Rank.Ace));
		tmpCards2.add(new Card(Suit.Hearts, Rank.Jack));
		tmpCards2.add(new Card(Suit.Spades, Rank.Ten));
		tmpCards2.add(new Card(Suit.Hearts, Rank.Five));		
		
		assertTrue(HandType.OnePair.compareTieBreaker(tmpCards, tmpCards2)<0);
	}
	
	@Test
	void TestTieBreakOnePairEven() {
		ArrayList<Card> tmpCards = new ArrayList<Card>();
		tmpCards.add(new Card(Suit.Clubs, Rank.Ace));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Ace));
		tmpCards.add(new Card(Suit.Hearts, Rank.Seven));
		tmpCards.add(new Card(Suit.Clubs, Rank.Nine));
		tmpCards.add(new Card(Suit.Diamonds, Rank.Three));
		
		ArrayList<Card> tmpCards2 = new ArrayList<Card>();
		tmpCards2.add(new Card(Suit.Clubs, Rank.Ace));
		tmpCards2.add(new Card(Suit.Diamonds, Rank.Ace));
		tmpCards2.add(new Card(Suit.Hearts, Rank.Seven));
		tmpCards2.add(new Card(Suit.Spades, Rank.Nine));
		tmpCards2.add(new Card(Suit.Hearts, Rank.Three));		
		
		assertTrue(HandType.OnePair.compareTieBreaker(tmpCards, tmpCards2)==0);
	}

}
