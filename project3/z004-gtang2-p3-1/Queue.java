/**************************************************************************
  * @author George Tang
  * CS310 Spring 2018
  * Project 3
  * George Mason University
  * 
  * File Name: Queue.java
  *
  * Description: This is the class for a generic queue (FIFO)
  * 
  ***************************************************************************/

public class Queue<AnyType>{
  
  /**
   * This is a nested class for handling the nodes of the queue  
   **/
  private static class Node<AnyType> {
    private AnyType value;
    private Node<AnyType> next;
  }  
  
  private Node<AnyType> first;
  private Node<AnyType> last;
  private int size;
  
  /**
   * Queue Constructor for initializing internal attributes    
   **/
  public Queue() {
    first = null;
    last = null;
    size = 0;    
  }
  
  /**
   * This method checks whether the queue is empty or not
   *
   * @return true if empty or false if not
   **/
  public boolean isEmpty() {
    // O(1)
    return size == 0;
  }
  
  /**
   * This method peeks the front element
   *
   * @return the front element or null if the queue is empty
   **/
  public AnyType getFront() {
    // O(1)
    if (isEmpty())
      return null;
    else
      return first.value;
  }
  
  /**
   * This method adds an element to the back
   *
   * @param value the element of AnyType to add to the queue    
   **/  
  public void enqueue(AnyType value) {
    // O(1)
    // temporarily hold the last element while a new Node is created for the value
    Node<AnyType> temp = last;
    last = new Node<AnyType>();
    last.value = value;
    last.next = null;
    // then set new element to be the last and be pointed by the previous last
    // if it is not the first element
    if (isEmpty()) 
      first = last;
    else           
      temp.next = last;
    size++;
  }
  
  /**
   * This method removes the front element
   *
   * @return the value of the front element or null if the queue is empty
   **/
  public AnyType dequeue(){
    // O(1)
    if (isEmpty())
      return null;
    // set the next element to be the first
    AnyType value = first.value;
    first = first.next;
    size--;
    return value;
  }
  
  //----------------------------------------------------
  // example testing code... make sure you pass all ...
  // and edit this as much as you want!
  
  public static void main(String[] args){
    Queue<Integer> iq = new Queue<Integer>();
    if (iq.isEmpty() && iq.getFront()==null){
      System.out.println("Yay 1");
    }
    
    iq.enqueue(new Integer(12));
    iq.enqueue(new Integer(20));
    iq.enqueue(new Integer(-233));
    
    if (iq.dequeue()==12 && iq.getFront()==20 ){
      System.out.println("Yay 2");
    }
    
    if (iq.dequeue()==20 && iq.dequeue()==-233 && iq.isEmpty() ){
      System.out.println("Yay 3");
    }
    
  }
  
}