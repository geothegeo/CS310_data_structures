/**************************************************************************
  * @author George Tang
  * CS310 Spring 2018
  * Project 3
  * George Mason University
  * 
  * File Name: Stack.java
  *
  * Description: This is the class for a generic stack (FILO)
  * 
  ***************************************************************************/

public class Stack<AnyType>{
  
  /**
   * This is a nested class for handling the nodes of the stack 
   **/
  private static class Node<AnyType> {
    private AnyType value;
    private Node<AnyType> next;
  }
  
  private Node<AnyType> first;
  private int size;
  
  /**
   * Stack Constructor for initializing internal attributes    
   **/  
  public Stack() {
    first = null;
    size = 0;
  }
  
  /**
   * This method checks whether the stack is empty or not
   *
   * @return true if empty or false if not
   **/  
  public boolean isEmpty() {
    // O(1)
    return size == 0;
  }

  /**
   * This method peeks the top element
   *
   * @return the top element or null if the stack is empty
   **/
  public AnyType peek() {
    // O(1)
    if(isEmpty())
      return null;
    else
      return first.value;
  }

  /**
   * This method adds an element to the top
   *
   * @param value the element of AnyType to add to the stack   
   **/  
  public void push(AnyType value) {
    // O(1)
    // temporarily hold the top element while a new Node is created for the value
    Node<AnyType> temp = first;
    first = new Node<AnyType>();
    first.value = value;
    // then set new element to be the first and point to the previous first
    // if it is not the first element    
    first.next = temp;
    size++;        
  }
  
  /**
   * This method removes the top element
   *
   * @return the value of the top element or null if the stack is empty
   **/
  public AnyType pop(){
    // O(1)
    if(isEmpty())
      return null;
    // set the next element to be the top
    AnyType topValue = first.value;
    first = first.next;
    size--;
    return topValue;     
  }
  
  //----------------------------------------------------
  // example testing code... make sure you pass all ...
  // and edit this as much as you want!
  
  public static void main(String[] args){
    Stack<Integer> iStack = new Stack<Integer>();
    if (iStack.isEmpty() && iStack.peek()==null){
      System.out.println("Yay 1");
    }
    
    iStack.push(new Integer(12));
    iStack.push(new Integer(20));
    iStack.push(new Integer(-233));
    
    if (iStack.pop()==-233 && iStack.peek()==20 ){
      System.out.println("Yay 2");
    }
    
    if (iStack.pop()==20 && iStack.pop()==12 && iStack.isEmpty() ){
      System.out.println("Yay 3");
    }
    
    
  }
  
}