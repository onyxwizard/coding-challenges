package DSA.ch13_LinkedList.basic;
/**
 * @author onyxwizard
 * @date 29-12-2025
 */

public class SingleLinkedList {
  private Node head;
  private int size;

  SingleLinkedList(int data) {
    head = new Node(data);
    size++;
  }

  // SingleLinkedList(int data, Node next) {
  //   head = new Node(data);
  //   head.next = next;
  //   size++; <-- problem need to count each node so No!
  // }

  //Insert at Beginning
  public void addFront(int data) {
    Node newNode = new Node(data);
    if (size == 0) {
      head = newNode;
    } else {
      newNode.next = head;
      head = newNode;
    }
    size++;
  }

  //Insert at end
  public void add(int data) {
    Node newNode = new Node(data);
    if (size == 0) {
      head = newNode;
    } else {
      Node current = head;
      while (current.next != null) {
        current = current.next;
      }
      current.next = newNode;
    }
    size++;
  }

  // Insert at specific position
  public void add(int data, int idx) {
    Node newNode = new Node(data);
    if (size == 0) {
      head = newNode;
    } else if (idx >= 0 && idx < size) {
      Node current = head;
      for (int i = 0; i < idx - 1; i++) {
        current = current.next;
      }
      newNode.next = current;
      current.next = newNode;
    }
    else {
      throw new IndexOutOfBoundsException();
    }
    size++;
  }

  public void display() {
    if (size == 0) {
      System.out.println("LL is Empty!");
      return;
    }
    Node current = head;
    while (current != null) {
      System.out.print(current.data);
      if (current.next != null)
        System.out.print(" -> ");
      current = current.next;
    }
    System.out.println("");
  }
  
  public int getSize() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }


  public static void main(String[] args) {
    SingleLinkedList sl = new SingleLinkedList(1);
    System.out.println(sl.getSize());
    System.out.println(sl.isEmpty());
    sl.add(2);
    sl.add(3);
    sl.add(4);
    sl.add(5);
    sl.add(6);
    sl.add(7);
    sl.display();
    
    sl.addFront(10);
    sl.display();
    
    sl.add(20,0);
    sl.display();
  }
}
