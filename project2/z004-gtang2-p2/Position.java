/**************************************************************************
  * @author George Tang
  * CS310 Spring 2018
  * Project 2
  * George Mason University
  * 
  * File Name: Postition.java
  *
  * Description: Class for which the key of the hashtable will be created with,
  * also represent one cell position in a 2D grid
  * 
  ***************************************************************************/

class Position{
  
  // row and column
  private int row;
  private int col;
  
  // constructor to initialize your attributes
  public Position(int row, int col){    
    this.row = row;
    this.col = col;
  }
  
  // accessors of row and column
  public int getRow(){ return row;}
  public int getCol(){ return col;}
  
  // return string representation of a position
  public String toString(){    
    // row R and col C must be represented as <R,C> with no spaces
    return "<" + row + "," + col + ">";
  }
  
  // check whether two positions are the same
  // return true if they are of the same row and the same column
  // return false otherwise
  @Override
  public boolean equals(Object obj){
    if(getRow() == ((Position)obj).getRow() && getCol() == ((Position)obj).getCol())
      return true;
    else 
      return false;
  }
  
  // compute an integer hash code for this object
  // must follow hash contract and distribute well
  @Override
  public int hashCode(){
    return (row+col)*row+col;
  }
   
  //----------------------------------------------------
  // example testing code... make sure you pass all ...
  // and edit this as much as you want!
   
  public static void main(String[] args){
    Position p1 = new Position(3,5);
    Position p2 = new Position(3,6);
    Position p3 = new Position(2,6);
    
    if (p1.getRow()==3 && p1.getCol()==5 && p1.toString().equals("<3,5>")){
      System.out.println("Yay 1");
    }
    
    if (!p1.equals(p2) && !p2.equals(p3) && p1.equals(new Position(3,5))){
      System.out.println("Yay 2");
    }
    
    if (p1.hashCode()!=p3.hashCode() && p1.hashCode()!=(new Position(5,3)).hashCode() &&
        p1.hashCode() == (new Position(3,5)).hashCode()){
      System.out.println("Yay 3");   
    }
    
    
  }
  
}