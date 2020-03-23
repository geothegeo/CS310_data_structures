/**************************************************************************
  * @author George Tang
  * CS310 Spring 2018
  * Project 2
  * George Mason University
  * 
  * File Name: TentTree.java
  *
  * Description: Class for a simplified Tent-Tree puzzle
  * 
  ***************************************************************************/

class TentTree{
  
  private int numRows, numCols; // size of the 2D board
  private HashMap<Position, String> grid; // the board stored in a hash table
  private String treeSymbol, tentSymbol;  // the string representing tree/tent on board
  
  // constructor that initializes attributes
  public TentTree(int numRows, int numCols, String tent, String tree){    
    grid = new HashMap<>();
    this.numRows = numRows;
    this.numCols = numCols;
    treeSymbol = tree;
    tentSymbol = tent;
  }
  
  // overloaded constructor that by default uses "X" for tent 
  // and "O" (capital O not 0) for tree
  public TentTree(int numRows, int numCols){
    grid = new HashMap<>();
    this.numRows = numRows;
    this.numCols = numCols;
    // defaults
    treeSymbol = "O";
    tentSymbol = "X";
  }
  
  // accessors that return tree/tent representation, O(1)
  public String getTentSymbol(){ return tentSymbol;}
  public String getTreeSymbol(){ return treeSymbol;}
  
  // accessors that return number of rows/columns, O(1)
  public int numRows(){ return numRows;}
  public int numCols(){ return numCols;}
  
  // check whether the specified position is a valid position for the board
  // return true for valid positions and false for invalid ones  
  public boolean isValidPosition(Position pos){
    // O(1)
    if(pos.getRow() < numRows && pos.getCol() < numCols)
      return true;
    return false;
  }
  
  // check whether the specified string s is a valid tent or tree symbol of the game
  public boolean isValidSymbol(String s){    
    // O(1)
    if(s.equals(treeSymbol) || s.equals(tentSymbol))
      return true;
    return false;
  }
  
  // set the cell at the specified position pos to be the specified string s 
  // do not change the board if invalid position: return false
  // do not change the board if invalid symbol: return false
  // do not change the board if the position is already occupied (not empty): return false
  // return true if board changed successfully
  public boolean set(Position pos, String s){
    // assuming HashMap overhead constant, O(1)
    if(isValidPosition(pos) && isValidSymbol(s) && grid.getValue(pos) == null)   
      return grid.add(pos, s);
    return false;
  }
 
  // return the cell at the specified position pos 
  // if invalid position: return null
  // if empty cell, return null
  public String get(Position pos){
    // assuming HashMap overhead constant, O(1)
    if(isValidPosition(pos) && grid.getValue(pos) != null)
       return grid.getValue(pos);
    return null;
  }

  // add another tent at the specified position pos
  // return false if a new tent cannot be added at pos 
  //     (i.e. attempt fails if pos is already occupied)
  // return true otherwise
  public boolean addTent(Position pos){
    // assuming HashMap overhead constant, O(1)
    if(isValidPosition(pos) && grid.getValue(pos) == null)
       return grid.add(pos, tentSymbol);
    return false;
  }
  
  // remove the tent from position pos
  // return false if the attempt of removal cannot be performed
  // return true otherwise
  public boolean removeTent(Position pos){
    // assuming HashMap overhead constant, O(1)
    if(isValidPosition(pos) && grid.getValue(pos) != null)
         return grid.remove(pos);
    return false;
    
  }

  // add another tree at the specified position pos
  // return false if a new tree cannot be added at pos
  //     (i.e. attempt fails if pos is already occupied)
  // return true otherwise
  public boolean addTree(Position pos){
    // assuming HashMap overhead constant, O(1)
    if(isValidPosition(pos) && grid.getValue(pos) == null)
       return grid.add(pos, treeSymbol);
    return false;
  }

  // check whether there is a tent at position pos
  // return true if yes and false otherwise
  // return false for invalid positions
  public boolean hasTent(Position pos){
    // assuming HashMap overhead constant, O(1)
    if(isValidPosition(pos))
      return grid.has(pos, tentSymbol);
    return false;    
  }

  // check whether at least one of the 4-way neighbors 
  // of the specified position pos has a symbol as the incoming string s
  //
  // The four direct neighbors of a pos is shown as below: up/down/left/right
  //       ---   U   ---
  //        L   pos   R
  //       ---   D   ---
  // 
  // if at least one of the four cells has string s as the symbol, return true;
  // return false otherwise
  public boolean posHasNbr(Position pos, String s){
    // assuming HashMap overhead constant, O(1)
    // Check D if it it exists
    if(pos.getRow() < numRows){
      if(s.equals(grid.getValue(new Position(pos.getRow()+1, pos.getCol()))))
        return true;
    }
    // Check U if it it exists
    if(pos.getRow() > 0){
      if(s.equals(grid.getValue(new Position(pos.getRow()-1, pos.getCol()))))
        return true;
    }
    // Check R if it it exists
    if(pos.getCol() < numCols-1){
      if(s.equals(grid.getValue(new Position(pos.getRow(), pos.getCol()+1))))
        return true;
    }
    // Check L if it it exists
    if(pos.getCol() > 0){
      if(s.equals(grid.getValue(new Position(pos.getRow(), pos.getCol()-1))))
        return true;
    }      
    return false;    
  }

  // check whether at least one of the 8 (horizontal/vertical/diagonal) neighbors 
  // of the specified position pos has a symbol as the incoming string s
  //
  // The eight horizontal/vertical/diagonal neighbors of a pos is shown as below: 
  // up left / up / up right / left / right / down left/ down/ down right
  //
  //        UL   U   UR
  //        L   pos   R
  //        DL   D   DR
  // 
  // if at least one of the eight cells has string s as the symbol, return true;
  // return false otherwise
  public boolean posTouching(Position pos, String s){
    // assuming HashMap overhead constant, O(1)
    
    if(pos.getRow() > 0 && pos.getRow() < numRows-1){
      // Check L if it it exists
      if(pos.getCol() > 0) {
        if(s.equals(grid.getValue(new Position(pos.getRow(), pos.getCol()-1))))
          return true; 
      }
      // Check R if it it exists
      if(pos.getCol() < numCols-1) {
        if(s.equals(grid.getValue(new Position(pos.getRow(), pos.getCol()+1))))
          return true; 
      }
    }
    
    if(pos.getRow() > 0){
      // Check U if it it exists
      if(pos.getCol() > 0 && pos.getCol() < numCols-1) {
        if(s.equals(grid.getValue(new Position(pos.getRow()-1, pos.getCol()))))
          return true; 
      }
      // Check UL if it it exists
      if(pos.getCol() > 0) {
        if(s.equals(grid.getValue(new Position(pos.getRow()-1, pos.getCol()-1))))
          return true; 
      }
      // Check UR if it it exists
      if(pos.getCol() < numCols-1) {
        if(s.equals(grid.getValue(new Position(pos.getRow()-1, pos.getCol()+1))))
          return true; 
      }
    }    
    
    if(pos.getRow() < numRows){
      // Check D if it it exists
      if(pos.getCol() > 0 && pos.getCol() < numCols-1) {
        if(s.equals(grid.getValue(new Position(pos.getRow()+1, pos.getCol()))))
          return true; 
      }
      // Check DL if it it exists
      if(pos.getCol() > 0) {
        if(s.equals(grid.getValue(new Position(pos.getRow()+1, pos.getCol()-1))))
          return true; 
      }
      // Check DR if it it exists
      if(pos.getCol() < numCols-1) {
        if(s.equals(grid.getValue(new Position(pos.getRow()+1, pos.getCol()+1))))
          return true; 
      }
    }
    
    return false;     
  }
  
  /***
    * methods that return a string of the board representation
    * this has been implemented for you: DO NOT CHANGE
    * @param no parameters
    * @return a string
    */
  @Override
  public String toString(){
    // return a string of the board representation following these rules:
    // - if printed, it shows the board in R rows and C cols
    // - every cell should be represented as a 5-character long right-aligned string
    // - there should be one space between columns
    // - use "-" for empty cells
    // - every row ends with a new line "\n"    
    StringBuilder sb = new StringBuilder("");
    for (int i=0; i<numRows; i++){
      for (int j =0; j<numCols; j++){
        Position pos = new Position(i,j);
        
        // use the hash table to get the symbol at Position(i,j)
        if (grid.contains(pos))
          sb.append(String.format("%5s ",this.get(pos)));
        else
          sb.append(String.format("%5s ","-")); //empty cell
      }
      sb.append("\n");
    }
    return sb.toString();
    
  } 
  
  /***
    * EXTRA CREDIT:
    * methods that checks the status of the grid and returns:
    * 0: if the board is empty or with invalid symbols
    * 1: if the board is a valid and finished puzzle
    * 2: if the board is valid but not finished
    *     - should only return 2 if the grid missing some tent but otherwise valid
    *       i.e. no tent touching other goodTents; no 'orphan' goodTents not attached to any tree, etc. 
    * 3: if the board is invalid
    *     - note: only one issue need to be reported when the grid is invalid with multiple issues
    * @param no parameters
    * @return an integer to indicate the status
    * 
    * assuming HashMap overhead constant, O(R*C) 
    * where R is the number of rows and C is the number of columns
    * Note: feel free to add additional output to help the user locate the issue
    */
  public int checkStatus(){
    // declare and initialize counters
    int trees = 0;
    int goodTrees = 0;
    int goodTents = 0;
    int spaces = 0;
    // go through each cell in the grid
    for(int i = 0; i < numRows; i++){
      for(int j = 0; j < numCols; j++){
        // if the cell is null then increment space
        if(get(new Position(i,j)) == null)
          spaces += 1;
        // if the cell is a tent ...
        else if(get(new Position(i,j)).equals("X")){
          // if there is another tent touching it then return 3
          if(posTouching(new Position(i,j),"X"))
            return 3;
          // else if it is touching a tree properly, then increment goodTents
          else if(posHasNbr(new Position(i,j),"O"))
            goodTents += 1;
          // else it is alone so return 3
          else
            return 3;
        }
        // if the cell is a tree ...
        else if(get(new Position(i,j)).equals("O")){
          // if it is touching a tent properly, then increment goodTrees
          if(posHasNbr(new Position(i,j),"X"))
             goodTrees += 1;
        // increment trees (keep track of total number of trees)
        trees += 1;
        }
        // else there is an invalid symbol so return 0
        else
            return 0;
      }
    }
    // if the number of valid trees and tents (touching one another) is not equal
    // or if the number of trees does not equal valid trees (then there are trees with no tents) ...
    if(goodTrees != goodTents || trees != goodTrees){
      // if all empty spaces then return 0
      if(spaces == numRows*numCols)
        return 0;
      // if not all empty spaces ...
      else{
        // if there is not a single tree then return 0, else it means that the grid is not complete yet
        if(trees == 0)
          return 0;
        else
          return 2;
      }
    // else it means they have won
    }else
      return 1;
  }
  
  //----------------------------------------------------
  // example testing code... make sure you pass all ...
  // and edit this as much as you want!
  
  // Note: you will need working Position and SimpleMap classes to make this class working
  
  public static void main(String[] args){
    
    TentTree g1 = new TentTree(4,5,"Tent","Tree");
    if (g1.numRows()==4 && g1.numCols()==5 && g1.getTentSymbol().equals("Tent")
          && g1.getTreeSymbol().equals("Tree")){
      System.out.println("Yay 1");
    }
    
    TentTree g2 = new TentTree(3,3);
    
    if (g2.set(new Position(1,0), "O") && !g2.set(new Position(1,0),"O") &&
        g2.addTree(new Position(1,2)) && !g2.addTree(new Position(1,5))){
      System.out.println("Yay 2");
    }
    
    if (g2.addTent((new Position(0,0))) && g2.addTent(new Position(0,1)) &&
        !g2.addTent((new Position(1,0))) && g2.get((new Position(0,0))).equals("X")){
      System.out.println(g2.get(new Position(0,0)));
      System.out.println("Yay 3");
      
    }
    
    if (g2.hasTent(new Position(0,0)) && g2.posHasNbr((new Position(0,0)),"O") &&
        g2.posTouching((new Position(1,2)),"X") && !g2.posHasNbr((new Position(1,2)),"X")){
      System.out.println("Yay 4");
      
    }
    if (g2.removeTent(new Position(0,1)) && !g2.removeTent(new Position(2,1))
          && g2.get(new Position(2,2))==null){
      System.out.println("Yay 5");
    }
    
  }
  
}