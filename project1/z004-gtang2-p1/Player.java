/**************************************************************************
  * @author George Tang
  * CS310 Spring 2018
  * Project 1
  * George Mason University
  * 
  * File Name: Player.java
  *
  * Description: Class with basic functionalities for a player to have a name,
  * score points, manipulate cards in his/her hand, and set a next player for
  * future linked list manipulation
  * 
  ***************************************************************************/
class Player <T extends Card> {
  
  // required fields
  private String name;
  private int points;
  private Hand<T> hand; 
  private Player<T> next;
  
  // constructor
  public Player(String name){
    // store the name
    this.name = name;
    // start with 0 points
    points = 0;
    // new hand
    hand = new Hand();
    // not pointing anyone next
    next = null;
  }
  
  // set next player
  public void setNext(Player<T> p){   
    next = p;
  }
  
  // return the next player
  public Player<T> getNext(){    
    return next;
  }
  
  // check whether there is a player after me
  // return false if there is not (or next is null)
  public boolean hasNext() {
    if(next == null)
      return false;
    else
      return true;
  }
  
  // return points of this player
  public int getPoints(){   
    return points;
  }
  
  // return name of the player
  public String getName(){    
    return name;
  }
  
  // receive a card and add it to hand
  // return false if card already exists
  public boolean receiveCard(T c){    
    if(hasCard(c) == true)
      return false;
    else {
      // first add the card to hand, then rack the points up, and return true
      hand.addCard(c); 
      points += c.getPoints();
      return true;  
    }
  }
  
  // return checking: false if the card is not in hand
  public boolean hasCard(T c){   
    if(hand.indexOf(c) == -1)
      return false;
    else
      return true;
  }
  
  // give away one card from hand
  // return false if card not present
  public boolean playCard(T c){   
    if(hasCard(c) == true) {
      // first deduct the points, then return the removed card
      points -= c.getPoints();
      return hand.removeCard(c);
    } else
      return false;
  }
  
  // give away the card at index
  // return the card given away at the index
  public T playCard(int index){
    // check if the card exits at the index   
    if(index >= 0 && index < hand.numCards()){
      points -= hand.getCard(index).getPoints();
      return hand.removeCard(index);      
    } else
      // throw RuntimeException for invalid index
      throw new RuntimeException();
  }
  
  
  
  //---------------------------------------------------
  // example test code... edit this as much as you want!
  // you will need working CardSwitch and Hand classes to run the given code
  
  
  public String toString(){
    // Not required; edit for your own testing 
    return "Player: " + name + "\n" + "Points: " + getPoints() + "\n" + hand.toString();
  }
  
  public static void main(String[] args) {
    CardSwitch card1 = new CardSwitch(Card.Rank.ACE, Card.Suit.SPADES);
    CardSwitch card2 = new CardSwitch(Card.Rank.JACK, Card.Suit.SPADES);
    CardSwitch card3 = new CardSwitch(Card.Rank.NINE, Card.Suit.HEARTS);
    Player<CardSwitch> player1 = new Player<CardSwitch>("Tom");
    Player<CardSwitch> player2 = new Player<CardSwitch>("Jerry");
    
    player1.receiveCard(card2);
    player1.receiveCard(card3);
    player2.receiveCard(card1);
    player1.setNext(player2);
    
    System.out.println(player1.toString());
    System.out.println(player2.toString());
    
    if (player1.getName().equals("Tom") && player1.getNext() == player2){
      System.out.println("Yay 1");
    }
    if (player1.hasCard(card2) == true && player1.getPoints() == 19){
      System.out.println("Yay 2");
    }
    if ((player2.hasNext()== false) && player1.playCard(0) == card2){
      System.out.println("Yay 3");
    }
    
  }
  
}