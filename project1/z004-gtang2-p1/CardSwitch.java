/**************************************************************************
  * @author George Tang
  * CS310 Spring 2018
  * Project 1
  * George Mason University
  * 
  * File Name: CardSwitch.java
  *
  * Description: Class with basic functionalities for creating the actual 
  * cards, comparing cards, and assigning point values to cards
  * 
  ***************************************************************************/

public class CardSwitch extends Card{
  
  // TO DO: fill the code below and add JavaDoc
  
  // constructor to create card for the game Switch
  public CardSwitch(Rank r, Suit s){
    // pass the arguments to Card's constructor
    super(r, s);
  }
  
  // checks if two cards equal
  // returns a boolean
  @Override
  public boolean equals(Card anotherCard){
    // the rank and suit of the card must match to be equal
    if(getRank() == anotherCard.getRank() && getSuit() == anotherCard.getSuit())
      return true;
    else 
      return false;
  }
  
  @Override
  // return points of the card
  public int getPoints(){
    // Using a switch to compare the card rank to its enum value in the Card class
    switch (getRank()){
      case ACE:
        return 1;
      case TWO:
        return 2;
      case THREE:
        return 3;
      case FOUR:
        return 4;
      case FIVE:
        return 5;
      case SIX:
        return 6;
      case SEVEN:
        return 7;
      case EIGHT:
        return 8;
      case NINE:
        return 9;
      case TEN:
      case JACK:
      case QUEEN:
      case KING:
        return 10;     
    }    
    return 0;
  }
  
  @Override
  public String toString(){
    // convert card to string consisting of as "(rank,suit)"
    // see examples below for format
    return "(" + getRank() + "," + getSuit() + ")";
    
  }
  
  //----------------------------------------------------
  //example test code... edit this as much as you want!
  public static void main(String[] args) {
    CardSwitch card = new CardSwitch(Card.Rank.ACE, Card.Suit.SPADES);
    
    if (card.getRank().equals(Card.Rank.ACE)){
      System.out.println("Yay 1");
    }
    
    if (card.toString().equals("(ACE,SPADES)")){
      System.out.println("Yay 2");
    }
    
    if (card.getPoints()==1){
      System.out.println("Yay 3");
    }
  }
  
}