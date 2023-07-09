package com.example.appointmentplatform.utils;

import com.example.appointmentplatform.model.Person;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.UUID;

@Component
public class DoublyLinkedList<E>{

    private HashMap<UUID, Node> map = new HashMap<>();
    Node head, last;

    int size = 0;

    public static class Node {

        private Person val;
        private Node prev;
        private Node next;

        public Node(Person person){
            this.val = person;
        }


        public Person getVal() {
            return val;
        }

        public void setVal(Person val) {
            this.val = val;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }


    }
    public DoublyLinkedList(){}

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(Node node){
        if(head == null) {
            head = node;
            last = node;
        }else {
            last.next = node;
            node.prev = last;
            last = node;
        }

        map.put(node.val.getId(), node);
        size++;
    }

    public void delete(UUID id){
        if(map.get(id) == null) throw new NoSuchElementException("No such person");
        else{
            Node node = map.get(id);
            if(head == last){
                head = null;
                last = null;
            }else if(node == head){
                node.next.prev = null;
                head = node.next;
                node.next = null;
            }else if(node == last){
                node.prev.next = null;
                last = node.prev;
                node.prev = null;
            }else{
                node.prev.next = node.next;
                node.next.prev = node.prev;
                node.prev = null;
                node.next = null;
            }
            map.remove(id);
            size--;
        }
    }

    public boolean contains(UUID id){
        return map.get(id) != null;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getLast() {
        return last;
    }

    public void setLast(Node last) {
        this.last = last;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public HashMap<UUID, Node> getMap() {
        return map;
    }

    public void setMap(HashMap<UUID, Node> map) {
        this.map = map;
    }

    public String toString(){
        Node tmp = head;
        StringBuilder sb = new StringBuilder().append("[");
        while(tmp != null){
            sb.append(tmp.getVal().getId()).append(" ");
            tmp = tmp.next;
        }
        sb.append("]");
        return sb.toString();
    }




}
