/**************************************************************************
  * @author Yutao Zhong and Jitin Krishnan
  * CS310 Spring 2018
  * Project 1
  * George Mason University
  * 
  * File Name: Card.java
  *
  * Description: Abstract Card class from which a card class specific to
  * any game can be constructed. This file SHOULD NOT be modified.
  * 
  ***************************************************************************/

public abstract class Card {
  
  // create a list of the possible rank for a card
  enum Rank{
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;
  }
  
  // create a list of the possible suits for a card
  enum Suit{
    HEARTS, CLUBS, DIAMONDS, SPADES;
  }
  
  // declare the variables for the properties of a card
  protected Rank rank;
  protected Suit suit;
  
  // constructor
  // assign given card values to the properties
  public Card(Rank r, Suit s){
    rank = r;
    suit = s;
  }
  
  // return the rank of the card
  public Rank getRank(){
    return rank;
  }
  
  // return the suit of the card
  public Suit getSuit(){
    return suit;
  }
  
  abstract boolean equals(Card c);
  
  abstract int getPoints();
  
  @Override
  public abstract String toString();
  
}
