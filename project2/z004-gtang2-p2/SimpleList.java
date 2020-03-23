/**************************************************************************
  * @author George Tang
  * CS310 Spring 2018
  * Project 2
  * George Mason University
  * 
  * File Name: SimpleList.java
  *
  * Description: Class that uses a singular linked list implementation and
  * includes a basic iterator
  * 
  ***************************************************************************/

import java.util.Iterator;

class SimpleList<T> implements Iterable<T>{

  // private class for the internal node
  private class Node<T>{
    T value; // data to store
    Node<T> next; // link to the next node
    
    // constructor
    public Node(T value){
      this.value = value;
      this.next = null;
    }
  }
  
  private Node<T> head; // first node
  private Node<T> tail; // last node
  private int size = 0; // number of nodes
  
  //constructor
  public SimpleList(){  
    head = new Node<T>(null);
  }
  
  // add a new node to the end of the linked list to hold value
  public void add(T value){    
    // O(1) 
    // add value to the end of the linked list
    Node<T> node = new Node<T>(value);   
    if (size == 0){
      head = node;
    }
    else{
      tail.next = node;
    }    
    tail = node;
    size++;
  }
  
  // given a value, remove the first occurrence of that value
  // return true if value removed
  // return false if value not present  
  public boolean remove(T value){
    // O(N) where N is the number of nodes returned by size()
    // find the first index of the value
    int index = this.indexOf(value);
    if (index == -1)
      return false;    
    // delete from the beginning
    if (index == 0){      
      head = head.next;
      if (size == 1)
        tail = null;      
      // delete from middle
    } else {
      Node<T> previous = head;
      Node<T> current = head.next;
      // go through indexes and do relinking to skip the node to be removed
      for (int i = 0; i < index-1; i++){
        previous = current;
        current = current.next;
      }
      previous.next = current.next;      
      // delete from end
      if (index == size-1)
        tail = previous;    
    }
    // decrease the size and return true
    size --;
    return true;   
  }
  
  // return index of a value (0 to size-1)
  // if value not present, return -1
  public int indexOf(T value){
    // O(N)
    Node<T> current = head;
    // go through the LL until the value of a node matches the given value
    for (int i = 0; i < size; i++){
      if (current.value.equals(value))
        return i;
      current = current.next;
    }
    return -1;
  }
  
  // return true if value is present
  // false otherwise
  public boolean contains(T value){
    // O(N) where N is the number of nodes returned by size()
    int index = this.indexOf(value);
    if (index == -1)
      return false;  
    return true;
  }

  // search for the node with the specified value:
  // if not found, return null;
  // if found, RETURN VALUE STORED from linked list, NOT the incoming value
  // Note: two values might be considered "equal" but not identical
  //       example: Pair <k,v1> and <k,v2> "equal" for different v1 and v2 
  public T get(T value){
    // O(N) where N is the number of nodes returned by size()
    Node<T> current = head;
    for (int i = 0; i < size; i++){
      if (current.value.equals(value))
        return current.value;
      current = current.next;
    }
    return null;
  }
  
  //return how many nodes are there
  public int size(){
    //O(1)
    return size;
  }
  
  // return a basic iterator
  public Iterator<T> iterator(){
    return new LocalIterator();
  }
  
  // inner Iterator class
  private class LocalIterator implements Iterator<T>{
    private Node<T> iter;
    
    // constructor
    public LocalIterator()
    {
      iter = head;
    }
          
    // .hasNext()
    public boolean hasNext(){
      // O(1)
      return iter != null;
    }
    
    // .next()
    public T next(){
      // O(1)
      T hold = iter.value;
      iter = iter.next;
      return hold;
    }
  }
  
  // return a string representation of the LL
  public String toString(){
    StringBuilder s = new StringBuilder("a LList with " + size() + " items:");
    if (size>0){
      Node<T> current = head;
      int index = 0;
      while (current!=null){
        s.append(" ["+current.value+"]-->");
        current = current.next;
        index ++;
        if (index % 8 == 0){
          s.append("\n");
        }
      }
      s.append(" null\n");    
    }
    return s.toString();    
  }
  
  //----------------------------------------------------
  // example testing code... make sure you pass all ...
  // and edit this as much as you want!
  // also, consider add a toString() for your own debugging
  
  public static void main(String[] args){
    SimpleList<Integer> ilist = new SimpleList<Integer>();
    ilist.add(new Integer(11));
    ilist.add(new Integer(20));
    ilist.add(new Integer(5));
    
    if (ilist.size()==3 && ilist.contains(new Integer(5)) && 
        !ilist.contains(new Integer(2)) && ilist.indexOf(new Integer(20)) == 1){
      System.out.println("Yay 1");
    }
    
    System.out.println(ilist.toString());
    if (!ilist.remove(new Integer(16)) && ilist.remove(new Integer(11)) &&
       !ilist.contains(new Integer(11)) && ilist.get(new Integer(20)).equals(new Integer(20))){
      System.out.println("Yay 2");   
    } 

    System.out.println(ilist.toString());
    Iterator iter = ilist.iterator();
    if (iter.hasNext() && iter.next().equals(new Integer(20)) && iter.hasNext() &&
        iter.next().equals(new Integer(5)) && !iter.hasNext()){
      System.out.println("Yay 3");      
    }
    
  }
  
}