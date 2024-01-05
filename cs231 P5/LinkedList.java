/*
 * Hunter Broughton
 * CS231 
 * 3/1/2023
 * 
 * The LinkedList.java class implements the nessescary methods and fields to create the Linked List data structure
 * 
 * Additionally, this class implements the Iterable interface, to allow other classes to iterate through our linked list 
 * 
 * to run the final LandscapeDisplay.java and/or SocialAgentSimulation.java files, please compile this file first
and ensure your files are in the same directory

how to compile:
javac LinkedList.java
 */


//import nessescary libraries
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;



//creates the LinkedList class, of any object type, and ensures that it implments the Iterable interface
public class LinkedList<T> implements Iterable<T>, Queue<T>, Stack<T>{    

    //creates the Node class, which can store any data type
    private static class Node<T> {

        //creates fields for the data in the node and it's reference to the next node
        T data;
        Node<T> next;
        Node<T> prev;


        //node constructor

        public Node(T data){
            this(data, null);
        }

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public Node(T data, Node<T> next, Node<T> prev){
            this.data = data;
            this.next = next;
            this.prev = prev;
        }


        //gets the data in the node
        public T getData() {
            return data;
        }

        //sets the next reference in the node
        public void setNext(Node<T> newNext) {
            next = newNext;
        }

        //gets the next reference in the node
        public Node<T> getNext() {
            return next;
        }
    }

    //stores the size of the linked list
    private int size;

    //stores the first node in the linked list
    private Node<T> head;

    private Node<T> tail;

    //linked list constructor
    public LinkedList() {
        size = 0;
        head = null;
        tail = null;
    }

    // Convert a LinkedList to an ArrayList
    public ArrayList<T> toArrayList() {
        ArrayList<T> arr = new ArrayList<>();
        for (T i : this) {
            arr.add(i);
        }

        return arr;
    }

    //Return Iterator for the Iterable interface
    public Iterator<T> iterator() {
        return new MyIterator(head);
    }


    //creates a class MyIterator, which implements the Iterator interface
    private class MyIterator implements Iterator<T> {

        //field for the head node
        Node<T> head;

        //iterator constructor
        public MyIterator(Node<T> head) {
            this.head = head;
        }

        //checks if their is a node after the curent one
        public boolean hasNext() {
            if (head != null) {
                return true;
            }
            return false;
        }


        //returns the data from the head node and goes to the next node in the linked list
        public T next() {
            T temp = head.getData();
            head = head.next;
            return temp;
        }

        //optional
        public void remove() {
            assert (true);
        }
    }


    //adds an item to the start of the linked list
    public void add(T item) {

        Node<T> newNode = new Node<T>(item, head);
        newNode.setNext(head);
        head = newNode;
        size++;

    }

    //gets the data at a specified index of the linked list
    public T get(int index) {
        Node<T> walker = head;

        for (int i = 0; i < index; i++) {
            walker = walker.getNext();
        }
        return walker.getData();
    }

    //adds a new node at a specified index of a linked list
    public void add(int index, T item){
        if (index == 0) {
            add(item);
            return;
        }

        Node<T> walker = head;
        for (int i = 0; i < index - 1; i++) {
            walker = walker.getNext();
        }

        Node<T> newNode = new Node<T>(item, walker.getNext());
        size++;
        walker.next = newNode;

    }

    //clears the contents of a linked list
    public void clear(){
        if(size == 0){
            return;
        }
        head = null;
        size = 0;
    }

    //checks if the linked list contains a specified object 
    public boolean contains(Object o){
        Node<T> walker = head;
        for (int i = 0; i < size; i++) {
            if((walker.getData()).equals(o)){
                return true;
            }
            walker = walker.getNext();
        }
        return false;

    }

    //checks if the linked list equals another linked list
    public boolean equals(Object o){
        if (!(o instanceof LinkedList)) {
            return false;
        }
        LinkedList<T> oAsALinkedList = (LinkedList<T>) o;

        if (oAsALinkedList.size != size) {
            return false;
        }

        Node<T> d_walker = this.head;
        Node<T> o_walker = oAsALinkedList.head;
        for (int i = 0; i < size; i++) {
            if (!(d_walker.equals(o_walker))) {
                if (d_walker.getData().equals(o_walker.getData())) {
                    return true;
                }
                return false;
            }

            o_walker.getNext();
            d_walker.getNext();
        }
        return true;
    }

    //checks if the linked list is empty 
    public boolean isEmpty(){
        if(head == null){
            return true;
        }else{
            return false;
        }
    }

    //removes from the start of a linked list
    public T remove(){
        T info = get(0);
        head = head.getNext();
        return info;
    }

    //removes from a specified index in the linked list
    public T remove(int index){
        Node<T> walker = head;
        if(index == 0){
            return remove();
        }
        for (int i = 0; i < index - 1; i++) {
            walker = walker.getNext();
        }
        Node<T> leftNode = walker;
        T info = walker.getNext().getData();
        Node<T> rightNode =  walker.getNext().getNext();
        leftNode.setNext(rightNode);
        size++;
        return info;
    }
    public void addFirst(T data) {
        if (head != null) {
            Node<T> temp = new Node<>(data, head, null);
            head.prev = temp;
            head = temp;
        } else {
            Node<T> temp = new Node<>(data);
            head = temp;
            tail = temp;
        }
        size++;
    }

    public void addLast(T data) {
        if (tail != null) {
            Node<T> temp = new Node<>(data, null, tail);
            tail.next = temp;
            tail = temp;
        } else {
            Node<T> temp = new Node<>(data);
            head = temp;
            tail = temp;
        }
        size++;
    }
    public T removeFirst() {
        // This return statement is only to let the code compile as is.
        // When you are ready, replace this return statement with the correct
        // value.
        if (head == null) {
            return null;
        }

        T res = head.getData();
        if (head.getNext() != null) {
            head = head.getNext();
            head.prev = null;
        } else {
            head = null;
            tail = null;
        }

        size--;
        return res;
    }

    public T removeLast() {
        // TODO

        // This return statement is only to let the code compile as is.
        // When you are ready, replace this return statement with the correct
        // value.

        if ((tail == null) || (head == null)) {
            return null;
        }

        T res = tail.getData();
        if (tail.prev != null) {
            tail = tail.prev;
            tail.next = null;
        } else {
            head = null;
            tail = null;
        }

        size--;
        return res;
    }

    //adds a item to a queue
    public void offer(T item){
        addLast(item);
    }

    //removes an item from a queue
    public T poll(){
        return removeFirst();
    }

    //adds an item to a stack
    public void push(T item){
        addFirst(item);
    }

    //pops an item from a stack
    public T pop(){
        return removeFirst();
    }

    //checks the popping value of a stack
    public T peek(){
        if (head == null){
            return null;
        }else{
            return head.getData();
        }
    }

    //returns the size of the linked list
    public int size(){
        return size;
    }

    //returns a string representation of the linked list
    public String toString(){
        Node<T> walker = head;
        String returned = "";
        for(int i = 0; i < size; i++){
            returned += walker.getData() + " ";
            walker = walker.getNext();
        }
        return returned;
    }

    

}
