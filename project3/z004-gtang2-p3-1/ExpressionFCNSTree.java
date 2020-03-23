/**************************************************************************
  * @author George Tang
  * CS310 Spring 2018
  * Project 3
  * George Mason University
  * 
  * File Name: ExpressionFCNS.java
  *
  * Description: This is the class for a First-Child Next-Sibling expression tree
  * 
  ***************************************************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExpressionFCNSTree{
  
  //==========================
  // DO NOT CHANGE
  FCNSTreeNode root;
  public ExpressionFCNSTree(){
    root = null;
  }
  
  public ExpressionFCNSTree(FCNSTreeNode root){
    this.root = root;
  }
  
  public boolean equals(ExpressionFCNSTree another){
    return root.equals(another.root);
  }
  
  // END OF DO NOT CHANGE
  //==========================
  
  /**
   * Internal attributes 
   **/  
  private String stringForm = "";
  
  /**
   * This method calls on the size helper method.
   *
   * @return the value returned by the size helper method
   **/
  public int size(){
    return size(root);
  }
  
  /**
   * This method recursively calls itself to go through all the nodes.
   *
   * @param  n the FCNS Tree node to access
   * @return   the number of nodes traversed
   **/
  private int size(FCNSTreeNode n){
    if(n == null){
      return 0;
    }
    else{
      return 1 + size(n.firstChild) + size(n.nextSibling);
    }    
  }   

  /**
   * This method calls on the height helper method.
   *
   * @return the value returned by the height helper method
   **/
  public int height(){
    return height(root);    
  }

  /**
   * This method recursively calls itself to go through all the nodes and count the number of nodes
   *
   * @param  n the FCNS Tree node to access
   * @return   the height of the tree or -1 for null tree
   **/
  private int height(FCNSTreeNode n) {
    if (n == null)
      return -1;
    int leftSub = height(n.firstChild);
    int rightSub = height(n.nextSibling);
    // maintain the greater height
    return Math.max(leftSub, rightSub) + 1;
  }

  /**
   * This method calls on the countNode helper method.
   *
   * @param  s the string symbol to look for
   * @return   the value returned by the countNode helper method
   **/
  public int countNode(String s){
    return countNode(root, s);   
  }

  /**
   * This method recursively calls itself to go through all the nodes and count 
   * how many nodes in the tree are with the specified symbol s
   *
   * @param  n the FCNS Tree node to access
   * @param  s the string symbol to look for
   * @return   the number of nodes that contain the symbol
   **/
  private int countNode(FCNSTreeNode n, String s){
    if(n == null){
      return 0;
    }
    else{
      if(n.element.equals(s))
        return 1 + countNode(n.firstChild, s) + countNode(n.nextSibling, s);
      else 
        return countNode(n.firstChild, s) + countNode(n.nextSibling, s);
    }    
  }
    
  /**
   * This method calls on the countNan helper method.
   *
   * @return the value returned by the countNan helper method
   **/
  public int countNan(){
    return countNan(root); 
  } 

  /**
   * This method recursively calls itself to go through all the nodes and count 
   * how many nodes are marked as not-a-number
   *
   * @param  n the FCNS Tree node to access
   * @return   the number of nodes marked as not-a-number
   **/
  private int countNan(FCNSTreeNode n){
    if(n == null){
      return 0;
    }
    else{
      if(n.nan == true)
        return 1 + countNan(n.firstChild) + countNan(n.nextSibling);
      else 
        return countNan(n.firstChild) + countNan(n.nextSibling);
    }    
  }
  
  /**
   * This method does basic check and set up before calling on the toStringPreFix helper method.
   *
   * @return the string holding the pre-order tree traversal expression
   **/
  public String toStringPreFix(){
    if (root == null) {
      return "";
    // set the string to hold the expression to be empty if it is not already
    } else if (!stringForm.isEmpty()) {
      stringForm = "";
    }
    toStringPreFix(root);
    return stringForm; 
  }

  /**
   * This method recursively calls itself to go through all the nodes and create a string 
   * representation of pre-order tree traversal with a one single space after each tree node 
   * 
   * @param  n the FCNS Tree node to access
   **/  
  private void toStringPreFix(FCNSTreeNode n){
    if(n == null)
      return ;
    
    stringForm += n.element + " ";
    toStringPreFix(n.firstChild);
    toStringPreFix(n.nextSibling);
  }
  
  /**
   * This method does basic check and set up before calling on the toStringPostFix helper method.
   *
   * @return the string holding the post-order tree traversal expression
   **/  
  public String toStringPostFix(){
    if (root == null) {
      return "";
    } else if (!stringForm.isEmpty()) {
      stringForm = "";
    }
    toStringPostFix(root);
    return stringForm; 
  }

  /**
   * This method recursively calls itself to go through all the nodes and create a string 
   * representation of post-order tree traversal with a one single space after each tree node 
   * 
   * @param  n the FCNS Tree node to access
   **/   
  private void toStringPostFix(FCNSTreeNode n){
    if(n == null)
      return ;

    toStringPostFix(n.firstChild);
    toStringPostFix(n.nextSibling);
    stringForm += n.element + " ";
  }
  
  /**
   * This method creates a string representation of level-order (breadth-first) tree traversal
   * with one single space after each tree node 
   *
   * @return the post-order tree traversal expression
   **/    
  public String toStringLevelOrder(){
    String lvlOrder = "";
    if (root == null) {
      return lvlOrder;
    }
    // create an empty stacks to traverse and store the post-order traversal node order
    Stack<FCNSTreeNode> fcnsStack1 = new Stack<FCNSTreeNode>();
    Stack<FCNSTreeNode> fcnsStack2 = new Stack<FCNSTreeNode>();
   
    fcnsStack1.push(root);
    // pop the root from the stack1 and push it to stack2
    if (root.firstChild != null){
      FCNSTreeNode n = fcnsStack1.pop();
      fcnsStack1.push(root.firstChild);  
      fcnsStack2.push(n);
    }
    // keep running while the stack is not empty
    while (!fcnsStack1.isEmpty())
    {
      // pop an element from the stack1 and push it to stack2
      FCNSTreeNode n = fcnsStack1.pop();
      fcnsStack2.push(n);      
      // push the child and sibling of the popped node to the stack1 if they exist
      if (n.firstChild != null)
        fcnsStack1.push(n.firstChild);   
      if (n.nextSibling != null)
        fcnsStack1.push(n.nextSibling);
    }    
    // print the post-order traversal by traversing through the elements stored in stack2
    while (!fcnsStack2.isEmpty()) {
      FCNSTreeNode temp = fcnsStack2.peek();
      lvlOrder += temp.element + " ";
      temp = fcnsStack2.pop();
    }    
    return lvlOrder;
  }
  
  /**
   * This method creates opens a file, read in a one-line numeric expression in prefix notation, and 
   * construct a first-child-next-sibling expression tree base on the input
   * 
   * @param fileName the location of the file containing the expression, relative to the this file
   **/  
  public void buildTree(String fileName) throws FileNotFoundException{
    try {
      File file = new File(fileName);      
      Scanner in = new Scanner(file);
      String temp = in.nextLine();
      in.close();
      String postfix = "";
      for(int i = temp.length()-1; i>=0; i--){
        if(!(temp.charAt(i) == ' '))
          postfix += temp.charAt(i);
      }
      root = new FCNSTreeNode(String.valueOf(postfix.charAt(0)));
    // if there is any exception, root should be null
    } catch (FileNotFoundException ex) {
      root = null;
    } 
  }
  

  /**
   * This method constructs the binary tree representation from the current expression
   * 
   * @return the ExpressionBinaryTree created
   **/  
  public ExpressionBinaryTree buildBinaryTree(){
    ExpressionBinaryTree binary = new ExpressionBinaryTree();
    stringForm = toStringPreFix();
    binary.root = new BinaryTreeNode(String.valueOf(stringForm.charAt(0)));
    return binary;
  }

  /**
   * This method creates a string representation of the normal human-friendly infix expression
   * 
   * @return a human-friendly in-order traversal expression
   **/  
  public String toStringPrettyInFix(){
    return stringForm; 
  }

  /**
   * This recursive method evaluates the expression and marks every tree node's operand and operator attributes.
   * If there is a division by zero: keep node.value to be null and set node.nan to be true.
   * 
   * @return the integer value of the root node or null for a null tree
   **/  
  public Integer evaluate(){
    // return null for null tree
    if (root == null) {
      return null;
    }    
    return 0;
  }

  /**
   * This non-recursive method evaluates the expression
   * 
   * @return the integer value of the root node or null for a null tree
   **/  
  public Integer evaluateNonRec(){
    // return null for null tree
    if (root == null) {
      return null;
    }
    return 0;
  }
  
  //----------------------------------------------------
  // example testing code... make sure you pass all ...
  // and edit this as much as you want!
  
  public static void main(String[] args) throws FileNotFoundException{
    
    //     *                    *
    //   /  \                  /
    //  /    \                1
    //  1     +   ==>          \
    //       / \                +
    //      2   3              /
    //                        2
    //                         \
    //                          3
    //
    // prefix: * 1 + 2 3 (expr1.txt)
    
    FCNSTreeNode n1 = new FCNSTreeNode("3");
    FCNSTreeNode n2 = new FCNSTreeNode("2",null,n1);
    FCNSTreeNode n3 = new FCNSTreeNode("+",n2,null);
    FCNSTreeNode n4 = new FCNSTreeNode("1",null,n3);
    FCNSTreeNode n5 = new FCNSTreeNode("*",n4,null);
    ExpressionFCNSTree etree = new ExpressionFCNSTree(n5);
    
    System.out.println(etree.size());
    System.out.println(etree.height());
    System.out.println(etree.countNode("+"));
    System.out.println(etree.countNan());
    if (etree.size()==5 && etree.height()==4 && etree.countNan()==0 && etree.countNode("+") == 1){
      System.out.println("Yay 1");
    }
    
    System.out.println("Pre: " + etree.toStringPreFix());
    System.out.println("In: " + etree.toStringPrettyInFix());
    if (etree.toStringPreFix().equals("* 1 + 2 3 ") && etree.toStringPrettyInFix().equals("(1*(2+3))")){
      System.out.println("Yay 2");      
    }

    System.out.println("Post: " + etree.toStringPostFix());
    System.out.println("Level: " + etree.toStringLevelOrder());
    if (etree.toStringPostFix().equals("3 2 + 1 * ") && etree.toStringLevelOrder().equals("* 1 + 2 3 ")){
      System.out.println("Yay 3");
      
    }
    
    if (etree.evaluateNonRec() == 5)
      System.out.println("Yay 4");    
    
    if (etree.evaluate() == 5  && n4.value==1 && n3.value==5 && !n5.nan){
      System.out.println("Yay 5");      
    }
  
    ExpressionFCNSTree etree2 = new ExpressionFCNSTree();
    etree2.buildTree("expressions/expr1.txt"); // construct expression tree from pre-fix notation
    
    if (etree2.equals(etree)){
      System.out.println("Yay 6");
    }
    
    BinaryTreeNode bn1 = new BinaryTreeNode("1");
    BinaryTreeNode bn2 = new BinaryTreeNode("2");
    BinaryTreeNode bn3 = new BinaryTreeNode("3");
    BinaryTreeNode bn4 = new BinaryTreeNode("+",bn2,bn3);
    BinaryTreeNode bn5 = new BinaryTreeNode("*",bn1,bn4);
    ExpressionBinaryTree btree = new ExpressionBinaryTree(bn5);
    
    //construct binary tree from first-child-next-sibling tree
    ExpressionBinaryTree btree2 = etree.buildBinaryTree(); 
    if (btree2.equals(btree)){
      System.out.println("Yay 7");
    }
        
    ExpressionFCNSTree etree3 = new ExpressionFCNSTree();
    etree3.buildTree("expressions/expr5.txt"); // an example of an expression with division-by-zero
    if (etree3.evaluate() == null && etree3.countNan() == 1){
      System.out.println("Yay 8");
    }
   
  }
}


//=======================================
// Tree node class implemented for you
// DO NOT CHANGE
class FCNSTreeNode{
  
  //members
  String element; //symbol represented by the node, can be either operator or operand (integer)
  Boolean nan; //boolean flag, set to be true if the expression is not-a-number
  Integer value;  //integer value associated with the node, used in evaluation
  FCNSTreeNode firstChild;
  FCNSTreeNode nextSibling;
  
  //constructors
  public FCNSTreeNode(String el){
    element = el;
    nan = false;
    value = null;
    firstChild = null;
    nextSibling = null;
  }
  
  //constructors
  public FCNSTreeNode(String el,FCNSTreeNode fc, FCNSTreeNode ns ){
    element = el;
    nan = false;
    value = null;
    firstChild = fc;
    nextSibling = ns;
  }
  
  
  // toString
  @Override 
  public String toString(){
    return element.toString();
  }
  
  // compare two nodes 
  // return true if: 1) they have the same element; and
  //                 2) their have matching firstChild (subtree) and nextSibling (subtree)
  public boolean equals(FCNSTreeNode another){
    if (another==null)
      return false;
    
    if (!this.element.equals(another.element))
      return false;
    
    if (this.firstChild==null){
      if (another.firstChild!=null)
        return false;
    }
    else if (!this.firstChild.equals(another.firstChild))
      return false;
    
    if (this.nextSibling==null){
      if (another.nextSibling!=null)
        return false;
    }
    else if (!this.nextSibling.equals(another.nextSibling))
      return false;
    
    return true;
    
  }
  
}