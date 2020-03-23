/**************************************************************************
  * @author Yutao Zhong and Jitin Krishnan
  * CS310 Spring 2018
  * Project 1
  * George Mason University
  * 
  * File Name: Deck.java
  *
  * Description: Class representing a deck of cards with basic functionalities
  * of shuffling, adding, dealing, etc.
  *
  * TASK: Comment using JavaDoc and show the Big-O runtime of each method.
  * Code on this file should NOT be modified.
  * 
  ***************************************************************************/

public class Deck<T extends Card> {
  
  // declare a player's hand
  private Hand<T> setOfCards;
  
  // constructor
  public Deck(){
    // initialize the hand
    setOfCards = new Hand<T>();
  }
  
  // return false if the card already exits in hand
  // otherwise add it and return true
  public boolean addCard(T c){
  // O(1)
    if (hasCard(c))
      return false;
    setOfCards.addCard(c);
    return true;
  }
  
  // return false if the card already exits in hand
  public boolean hasCard(T c){
  // O(1)
    return (setOfCards.indexOf(c) != -1);    
  }
  
  // randomly shuffles the deck
  public void shuffle() {
  // O(N)
    for (int i = setOfCards.numCards()-1; i >= 0; i-- ) {
      // get a random integer based on number of cards left
      int rand = (int)(Math.random()*(i+1));
      // store the card at the current index
      T temp = setOfCards.getCard(i);
      // swap the stored card with one in at a random index
      setOfCards.setCard(i, setOfCards.getCard(rand));
      setOfCards.setCard(rand, temp);
    }
  }
  
  // give the top card away from the deck
  public T dealNextCard() {
  // O(1)  
    // return null if the deck has no cards.
    if(setOfCards.numCards()==0) 
      return null;
    // return the last card in the deck, remove it from the deck and update the card count
    T temp = this.setOfCards.removeCard(setOfCards.numCards()-1);
    return temp;
  }
  
  // return true if the deck is empty. Otherwise returns false
  public boolean isEmpty() {
  // O(1)
    return this.setOfCards.numCards() == 0;
  }
  
  // return the number of cards in the deck
  public int cardCount(){
  // O(1)
    return this.setOfCards.numCards();
  }
  
  // return a string consisting of the cards in the deck
  public String toString(){
  // O(1)
    StringBuilder sb = new StringBuilder("Deck ");
    int numCards = cardCount();
    if (numCards == 0){
      sb.append("currently with no cards.");
    }
    else{
      sb.append("with " + numCards+ " cards:\n");
      sb.append(setOfCards.toString());
    }
    return sb.toString();
  }
  
}
