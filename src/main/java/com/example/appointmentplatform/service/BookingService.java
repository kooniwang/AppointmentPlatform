package com.example.appointmentplatform.service;

import com.example.appointmentplatform.model.Person;
import com.example.appointmentplatform.utils.DoublyLinkedList;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class BookingService {

    private DoublyLinkedList<Person> normalQueue = new DoublyLinkedList<>();
    private DoublyLinkedList<Person> priorityQueue = new DoublyLinkedList<>();


    @PostConstruct
    public void initializedWaitingList(){
        Person p1 = new Person();
        p1.setId(UUID.randomUUID());
        p1.setFirstName("Jane");
        p1.setLastName("Doe");
        p1.setBirth(LocalDate.of(1983, 8, 14));
        p1.setIsPrioritized(false);
        p1.setStatus(Person.Status.WAITING);
        p1.setMsp("100 327 2668");
        p1.setDescription("Continuous cough");
        normalQueue.add(new DoublyLinkedList.Node(p1));



        Person p2 = new Person();
        p2.setId(UUID.randomUUID());
        p2.setFirstName("Kelly");
        p2.setLastName("Coco");
        p2.setBirth(LocalDate.of(1974, 3, 20));
        p2.setIsPrioritized(false);
        p2.setStatus(Person.Status.WAITING);
        p2.setMsp("013 288 1158");
        p2.setDescription("Limbs numb");
        normalQueue.add(new DoublyLinkedList.Node(p2));


        Person p3 = new Person();
        p3.setId(UUID.randomUUID());
        p3.setFirstName("Lily");
        p3.setLastName("Liu");
        p3.setBirth(LocalDate.of(2001, 1, 6));
        p3.setIsPrioritized(false);
        p3.setStatus(Person.Status.WAITING);
        p3.setMsp("083 115 3752");
        p3.setDescription("Finger cut");
        normalQueue.add(new DoublyLinkedList.Node(p3));

        Person p4 = new Person();
        p4.setId(UUID.randomUUID());
        p4.setFirstName("Sally");
        p4.setLastName("Done");
        p4.setBirth(LocalDate.of(1953, 9, 3));
        p4.setIsPrioritized(false);
        p4.setStatus(Person.Status.WAITING);
        p4.setMsp("005 814 2044");
        p4.setDescription("Intense chest pain");
        normalQueue.add(new DoublyLinkedList.Node(p4));

        Person p5 = new Person();
        p5.setId(UUID.randomUUID());
        p5.setFirstName("Susan");
        p5.setLastName("Chou");
        p5.setBirth(LocalDate.of(1976, 3, 22));
        p5.setIsPrioritized(false);
        p5.setStatus(Person.Status.WAITING);
        p5.setMsp("221 632 3382");
        p5.setDescription("Peanut butter allergy");
        normalQueue.add(new DoublyLinkedList.Node(p5));


    }


    public ArrayList<Person> getWaitingList(){
        ArrayList<Person> appointmentList = new ArrayList<>();
        if(priorityQueue != null){
            DoublyLinkedList.Node node = priorityQueue.getHead();
            while(node != null){
                appointmentList.add(node.getVal());
                node = node.getNext();
            }

        }
        if(normalQueue != null){
            DoublyLinkedList.Node node = normalQueue.getHead();
            while(node != null){
                appointmentList.add(node.getVal());
                node = node.getNext();
            }

        }
        return appointmentList;
    }


    public void cancelAppointment(UUID id){
        if(priorityQueue.contains(id)) priorityQueue.delete(id);
        else normalQueue.delete(id);

        //update person's status as CANCEL DB based on id
    }


    public void prioritizeAppointment(UUID id){
        if(normalQueue.getMap().get(id) == null){
            return;
        }
        Person person = normalQueue.getMap().get(id).getVal();
        person.setStatus(Person.Status.PRIORITIZED);
        person.setIsPrioritized(true);
        priorityQueue.add(new DoublyLinkedList.Node(person));
        normalQueue.delete(person.getId());

        //update person's status as PRIORITIZED DB based on id
    }


    public void makeAppointment(Person person){
        person.setId(UUID.randomUUID());
        person.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        person.setOrder(normalQueue.getSize()+1);
        person.setStatus(Person.Status.WAITING);
        if(person.getIsPrioritized()) priorityQueue.add(new DoublyLinkedList.Node(person));
        else normalQueue.add(new DoublyLinkedList.Node(person));

        //insert into DB the person's info
    }
}
