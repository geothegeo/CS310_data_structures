/**************************************************************************
  * @author George Tang
  * CS310 Spring 2018
  * Project 1
  * George Mason University
  * 
  * File Name: Hand.java
  *
  * Description: Class that uses an ArrayList implementation to represent
  * and manipulate the cards in a player's hand
  * 
  ***************************************************************************/

public class Hand<T extends Card>{
  
  // TO DO: add your implementation and JavaDoc
  
  private T [] cards;
  private int numCards;
  
  // constructor
  public Hand(){    
    // initial card capacity in one's hand is 5 with 0 cards in hand
    cards = (T []) new Card[5];
    numCards = 0;
  }
  
  // return the number of cards
  public int numCards(){    
    // O(1)
    return numCards;
  }   
  
  // return card at index 
  public T getCard(int index){     
    // O(1)
    // first check if the index is valid before returning
    if (index >=0 && index < numCards)
      return cards[index];
    else
      // throw RuntimeException for invalid index
      throw new RuntimeException();
  }
  
  // change the card at index to be c 
  public void setCard(int index, T c){       
    // O(1)
    // first check if the index is valid before changing
    if (index >=0 && index < numCards) {
      cards[index] = c;
    } else
      // throw RuntimeException for invalid index
      throw new RuntimeException();
  }
  
  // add card c at the "end" of the hand
  public void addCard(T c){    
    // O(N)    
    // check whether capacity has reached
    if (numCards == cards.length){      
      // if yes: expand array by first creating a new array of doubled size
      T [] newCards = (T []) new Card [numCards * 2];      
      // then copy over from old array
      for (int i = 0; i < numCards; i++){
        newCards[i] = cards[i];
      }      
      // then set the new array to the old array
      cards = newCards;
    }      
    // now add the card to the end and increase the number of cards
    cards[numCards] = c;   
    numCards++;
  }
  
  // find the index of a given card c, 
  // returns the index or -1 if not found 
  public int indexOf(T c){    
    // O(N) 
    for (int i = 0; i < numCards; i++) {
      // check for the match to the inquired card
      if (c.equals(cards[i])) {
        return i;
      }
    }
    return -1;
  }
  
  // remove the card at index
  // return the removed card
  public T removeCard(int index){  
    // O(N)
    // check if the index is valid
    if (index >= 0 && index < numCards){
      // store the item temporarily
      T tmp = cards[index];     
      // shift the array starting with the card to the left
      for (int j = index; j < numCards-1; j++){
        cards[j] = cards[j+1];
      }
      // the number of cards go down and the removed card is returned
      numCards --;
      return tmp;
    }
    else{
      // throw RuntimeException for invalid index
      throw new RuntimeException();
    }  
  }
  
  // remove card c
  // returns false if no such card
  public boolean removeCard(T c){   
    // O(N)
    // get the index of the card
    int i = indexOf(c);
    // check if the index is valid
    if (i >= 0 && i < numCards){     
      // shift the array starting with the card to the left to delete card
      for (int j = i ; j < numCards-1 ; j++){
        cards[j] = cards[j+1];
      }
      // the number of cards go down and true is returned for removing the card
      numCards --;
      return true;
    }
    else{
      return false;
    }
    
  }  
  
  // --------------------------------------------------------
  // example test code... edit this as much as you want!
  // you will need a working CardSwitch class to run the given code
  
  
  // Not required, update for your testing purpose
  @Override
  public String toString(){
    // return string representation of hand
    // update if you want to include information for all cards in hand
    
    String hand = "Hand with " + numCards + " cards \n";
    for (int i = 0; i < cards.length; i++) {
      if (cards[i] != null)
        hand += "card" + (i + 1) + " is " + getCard(i) + "\n";  
      else 
        hand += "card" + (i + 1) + " is null\n";
    }
    return hand;    
  }
  
  
  public static void main(String[] args) {
    
    CardSwitch card1 = new CardSwitch(Card.Rank.ACE, Card.Suit.SPADES);
    CardSwitch card2 = new CardSwitch(Card.Rank.JACK, Card.Suit.SPADES);
    CardSwitch card3 = new CardSwitch(Card.Rank.NINE, Card.Suit.HEARTS);
    
    Hand<CardSwitch> myHand = new Hand<CardSwitch>();
    myHand.addCard(card1);
    myHand.addCard(card2);
    
    if ((myHand.numCards() == 2) && (myHand.getCard(0).equals(card1))){
      System.out.println("Yay 1");
    }
    
    System.out.println(myHand.toString());
    myHand.addCard(card3);
    
    if ( card2.equals(myHand.removeCard(1)) && myHand.getCard(1).equals(card3)){
      System.out.println("Yay 2");
    }
    
    if ((myHand.indexOf(card1)==0) && (myHand.indexOf(card2) == -1 )){
      System.out.println("Yay 3");
    }
    
  }
  
  
}