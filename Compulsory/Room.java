package com.example.Compulsory;

public class Room {
    private String name;
    private RoomType type;
    private int capacity;

    public Room() {
        this.name = "";
        this.type = null;
        this.capacity = 0;

    }

    public Room(String name, RoomType type, int capacity) {
        this.setName(name);
        this.setType(type);
        this.setCapacity(capacity);
    }

    public String getName() {
        return this.name;
    }

    public RoomType getType() {
        return this.type;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name= " + this.name +
                ", type= " + this.type +
                ", capacity= " + this.capacity + '\'' + "}";
    }

   public void print(){
        System.out.println(this.toString());
    }
}