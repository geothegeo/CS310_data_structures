/**************************************************************************
  * @author George Tang
  * CS310 Spring 2018
  * Project 1
  * George Mason University
  * 
  * File Name: BoardSwitch.java
  *
  * Description: Class that uses an Circular Linked List implementation to 
  * manipulate the players around the board for their turns and plays
  * 
  ***************************************************************************/
public class BoardSwitch<T extends Card> extends Board<T>{

  //constructor
  public BoardSwitch(Deck<T> deck){
    // pass the deck to Board
    super(deck);
  }
  
  @Override
  // return the current player
  public Player<T> getCurrentPlayer() {  
    // O(1)
    return currentPlayer;
  }
  
  @Override
  // return how many players 
  public int getNumPlayers() {
    // O(1)
    return numPlayer;
  }
  
  @Override
  //return the current deck
  public Deck<T> getDeck(){    
    // O(1)
    return deck;
  }
  
  @Override
  // move the current player to the next one in the linked list
  // return false if cannot change
  public boolean changeTurn() {   
    // O(1)
    if(currentPlayer.hasNext()){
      currentPlayer = currentPlayer.getNext();
      return true;
    } else
      return false;
  }
  
  @Override
  // add another player in the linked list
  // should add to the left of currentPlayer
  public void addPlayer(Player<T> x) {
    // O(N)
    // if there are 0 players, set current and next to x
    if (numPlayer == 0){
      currentPlayer = x;
      currentPlayer.setNext(x);
      numPlayer ++;
    // if there is already 1 players, make the players point to
    // each other for circular linked list
    } else if (numPlayer == 1){
      x.setNext(currentPlayer);
      currentPlayer.setNext(x);
      numPlayer ++;
    // If there are already 2 players, add the player to the left
    // of the current player
    } else {
      // search for the plyer already to the left of the current
      Player tmp = currentPlayer;
      for(int i = 0; i < numPlayer; i++){        
        if(tmp.getNext() == currentPlayer){
          // set the x the next of the former left player
          tmp.setNext(x);
        } else {
          tmp = tmp.getNext();
        }
      }
      // set the currentPlayer the next of x
      x.setNext(currentPlayer);
      numPlayer ++;
    }
  }
  
  // return the player with the highest point
  public Player<T> findWinner(){    
    // O(N)
    // set the current player as the winner for now
    Player winner = currentPlayer;
    Player traverse = currentPlayer;
    int points = currentPlayer.getPoints();
    // traverse through the players
    for(int i = 0; i < numPlayer; i++){
      traverse = traverse.getNext();
      // if the points of a player is higher or equal to the current winner 
      if(traverse.getPoints() >= points){
        // and if the player is lexicologically smaller than the current winner 
        if(traverse.getName().toLowerCase().compareTo(winner.getName().toLowerCase()) < 0)
          // set the new player as the winner
          winner = traverse;
      }
    }
    return winner;
  }
  
  //-----------------------------------------------------
  // example test code... edit this as much as you want!
  // you will need working CardSwitch, Hand, Player, Deck and PlaySwitch classes to run the given code
  
  public static void main(String[] args) {
    Deck<CardSwitch> deck = new Deck<CardSwitch>();
    PlaySwitch.init_deck(deck);
    
    BoardSwitch<CardSwitch> myBoard = new BoardSwitch<CardSwitch>(deck);
    Player<CardSwitch> player1 = new Player<CardSwitch>("Tom");
    Player<CardSwitch> player2 = new Player<CardSwitch>("Jerry");
    
    myBoard.addPlayer(player1);
    
    if (myBoard.getNumPlayers() ==1  && myBoard.getCurrentPlayer() == player1
          && player1.getNext() == player1){
      System.out.println("Yay 1");
    }
    
    myBoard.addPlayer(player2);
    if (myBoard.getNumPlayers() ==2  && myBoard.getCurrentPlayer() == player1
          && (myBoard.changeTurn()==true) && myBoard.getCurrentPlayer() == player2){
      System.out.println("Yay 2");
    }
    
    player1.receiveCard(new CardSwitch(Card.Rank.ACE, Card.Suit.SPADES));
    player1.receiveCard(new CardSwitch(Card.Rank.JACK, Card.Suit.CLUBS));
    player2.receiveCard(new CardSwitch(Card.Rank.NINE, Card.Suit.HEARTS));
    player2.receiveCard(new CardSwitch(Card.Rank.THREE, Card.Suit.SPADES));
    
    if (player1.getNext() == player2 && player2.getNext() == player1
          && myBoard.findWinner() == player2){
      System.out.println("Yay 3");
    }   
    
  }
  
  
}
