package com.example.Compulsory;

public class Main {
    public static void main(String[] args) {
        Event event1 = new Event("C1", 50, 10,12);
        Event event2 = new Event("L1", 70, 11,16);
        Event event3 = new Event("C2", 30, 10,14);

        Room room1 = new Room("301",RoomType.lectureHall,50);
        Room room2 = new Room("300",RoomType.library,20);
        Room room3 = new Room("308",RoomType.computerLab,100);

        System.out.println("Events:");

        event1.print();
        event2.print();
        event3.print();

        System.out.println();

        System.out.println("Rooms:");

        room1.print();
        room2.print();
        room3.print();
    }
}
