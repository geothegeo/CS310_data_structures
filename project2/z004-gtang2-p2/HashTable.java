/**************************************************************************
  * @author George Tang
  * CS310 Spring 2018
  * Project 2
  * George Mason University
  * 
  * File Name: HashTable.java
  *
  * Description: Class that implements a hash table with seperate chaining
  * 
  ***************************************************************************/

import java.util.Arrays;
import java.util.Iterator;

class HashTable<T>{
  
  // necessary measurements
  private int len, items, chains;
  
  // constructor
  public HashTable(){
    // initialize the attributes
    len = 11;
    items = 0; 
    chains = 0;   
  }
  
  @SuppressWarnings("unchecked")
  private SimpleList<T>[] table = new SimpleList[11];
  
  // adds an item to the hash table
  // returns true if you successfully add value
  // returns false if the value can not be added
  public boolean add(T value) {
    // Overhead not considering rehash:
    // O(M) worst case, where M =  size returned by size()
    // O(1) or O(M/N) average case (where M/N is the load)
    // the average case can be amortized Big-O 
    
    // finding the index to store the value by % its hash code by the table length
    int index = value.hashCode() % len;
    // if the index at the table does not have a LL, then create one
    if(table[index] == null){
      table[index] = new SimpleList<T>();
      chains++;
    }
    // if the LL does not contain the value, then add the value in and increase the number of items
    if(!table[index].contains(value)){
      table[index].add(value);
      items++;
      // if the average chain length is > 1.2, rehash to a prime number that is larger
      // than twice the size before returning and keep expanding until rehash succeeds.
      if(getAvgChainLength() > 1.2)
        while(!rehash(nextPrime(len*2)));   
      return true;
    } else {
      return false;
    }          
  }

  // removes a value from the hash table
  // returns true if you remove the item
  // returns false if the item could not be found
  public boolean remove(T value) {
    // O(M) worst case, where M = size returned by size()
    // O(1) or O(M/N) average case (where M/N is the load)    
    // go through the table indexes ...
    for(int i = 0; i < table.length; i++) {
      // if there is a LL and it contains the value ...
      if(table[i] != null && table[i].contains(value)){
          // remove the value and decrease the number of items
          table[i].remove(value);
          items--;
          // is there are no items left in a LL, then decrease the number of chains
          if(table[i].size() == 0)
            chains--;
          return true;
      }
    }
    return false;
  }
  
  // returns true if the item can be found in the table
  public boolean contains(T value) {  
    // O(M) worst case, where M = size returned by size()
    // O(1) or O(M/N) average case (where M/N is the load)  
    for(int i = 0; i < table.length; i++) {
      if(table[i] != null && table[i].contains(value))
          return true;
    }
    return false;
  }
  
  // return null if the item could not be found in hash table;
  // return the item FROM THE HASH TABLE if it was found.
  // NOTE: do NOT return the parameter value!!
  //       While "equal" they may not be the same.
  //       For example, When value is a PAIR<K,V>, 
  //       its "equals" methods returns true if just the keys are equal.
  @SuppressWarnings("unchecked")
  public T get(T value) {   
    // O(M) worst case, where M = size returned by size()
    // O(1) or O(M/N) average case (where M/N is the load)  
    // go through the table indexes ...
    for (int i = 0; i < table.length; i++){
      // if there is a LL, then create an iterator
      if(table[i] != null){
        Iterator iter = table[i].iterator();
        // while the there is a next node ...
        while(iter.hasNext()){   
          // store the iter value until it equals the given value and return it
          T temp = (T) iter.next();
          if(temp.equals(value))
            return temp;
        }
      }
    }
    return null;
  }
  
  // rehash to a larger table size (specified as the
  // parameter to this method)
  // - return true if table gets resized
  // - if the newCapacity will make the load to be more than 0.7, do not resize
  //   and return false
  @SuppressWarnings("unchecked")
  public boolean rehash(int newCapacity) {   
    // O(N) when N>M; O(M) otherwise
    // where N is the table length and M = size returned by size()
    // set the table len to the newCapacity
    len = newCapacity;
    // if load is >.7, the exit (there will be another rehash)
    if(getLoad() > .7)
      return false;
    // create a new hash table (array) using the newCapacity as the size 
    SimpleList<T>[] newTable = new SimpleList[newCapacity];
    // need to recount chains so reset here
    chains = 0;
    
    // go through the old table indexes ...
    for (int i = 0; i < table.length; i++){
      // if there is a LL, then create an iterator
      if(table[i] != null){
        Iterator iter = table[i].iterator();   
        // while the there is a next node ...
        while(iter.hasNext()){
          // store the value of the node then do the add proccess here like above
          T temp = (T) iter.next();
          int index = temp.hashCode() % len;
          if(newTable[index] == null){
            newTable[index] = new SimpleList<T>();
            chains++;
          }
          newTable[index].add(temp);                          
        }      
      }
    }
    // set the old table equal to the new table
    table = newTable;
    return true;
  }
  
  // return the number of items in the table 
  public int size() {  
    // O(1)
    return items;
  }
  
  // return the load on the table
  public double getLoad() {   
    // O(1)
    return (double)items/len;
  }
  
  // return the average length of non-empty chains in the hash table
  public double getAvgChainLength(){    
    // O(1) 
    return (double)items/chains;
  }
  
  // take all the values in the table and put them
  // into an array with a size equal to the number of items
  public Object[] valuesToArray() {  
    // O(N) when N>M; O(M) otherwise
    // where N is the table length and M = size returned by size()
    // start a counter to go through the array indexes and create an array
    int count = 0;
    Object[] hashArray = new Object[size()];  
    
    // go through the values in the hash table ...
    for (int i = 0; i < table.length; i++){
      if(table[i] != null){
        Iterator iter = table[i].iterator();
        while(iter.hasNext()){         
          // add the value to the array and increment the counter
          hashArray[count] = iter.next();
          count++;
        }      
      }
    }
    return hashArray;
  }
  
  // inefficiently finds the next prime number >= x
  // this is written for you
  public int nextPrime(int x) {
    while(true) {
      boolean isPrime = true;
      for(int i = 2; i <= Math.sqrt(x); i++) {
        if(x % i == 0) {
          isPrime = false;
          break;
        }
      }
      if(isPrime) return x;
      x++;
    }
  }
 
  // return a representation of the hash table
  public String toString(){
    String s = "";
    for (int i = 0; i < table.length; i++){
      if(table[i] != null)
        s += i + " " + table[i].toString();
    }
    s += "\n";
    return s;
  }
  
  //------------------------------------
  // example test code... edit this as much as you want!
  
  
  public static void main(String[] args) {
    HashTable<String> names = new HashTable<>();
    
    if(names.add("Alice") && names.add("Bob") && !names.add("Alice") 
         && names.size() == 2 && names.getAvgChainLength() == 1)  {
      System.out.println("Yay 1");
    }
    
    if(names.remove("Bob") && names.contains("Alice") && !names.contains("Bob")
         && names.valuesToArray()[0].equals("Alice")) {
      System.out.println("Yay 2");
    }
    
    boolean loadOk = true;
    if(names.getLoad() != 1/11.0 || !names.rehash(10) || names.getLoad() != 1/10.0 || names.rehash(1)) {
      loadOk = false;
    }
    
    boolean avgOk = true;
    HashTable<Integer> nums = new HashTable<>();
    for(int i = 1; i <= 70 && avgOk; i++) {
      nums.add(i);
      double avg = nums.getAvgChainLength();
      System.out.println("-----"+nums.len+"-----"+nums.size()+"-----"+nums.getLoad()+"-----"+nums.getAvgChainLength()+"-----");
      System.out.println(nums.toString());     
      if(avg> 1.2 || (i < 12 && avg != 1) || (i >= 14 && i <= 23 && avg != 1) || 
         (i >= 28 && i <= 47 && avg != 1) || (i >= 57 && i <= 70 && avg!= 1)) {
        avgOk = false;
      }
      
    }
    if(loadOk && avgOk) {
      System.out.println("Yay 3");
    }
    
    System.out.println(Arrays.toString(nums.valuesToArray()));
    
  }
}