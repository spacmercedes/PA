package com.example.Compulsory;

public class Event {
   private String name;
   private int size;
   private int startTime;
   private int endTime;

   public Event() {
      this.name = "";
      this.size = 0;
      this.startTime = 0;
      this.endTime = 0;

   }

   public Event(String name, int size, int startTime, int endTime){
      this.setName(name);
      this.setSize(size);
      this.setStartTime(startTime);
      this.setEndTime(endTime);
   }

   public String getName() {
      return this.name;
   }

   public int getSize() {
      return this.size;
   }

   public int getStartTime() {
      return this.startTime;
   }

   public int getEndTime() {
      return this.endTime;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setSize(int size) {
      this.size = size;
   }

   public void setStartTime(int startTime) {
      this.startTime = startTime;
   }

   public void setEndTime(int endTime) {
      this.endTime = endTime;
   }

   @Override
   public String toString(){
      return "Event{"+
              "name="+this.name+
              ", size="+this.size+
              ", start="+this.startTime+'\''+
              ", end="+this.endTime+'\''+"}";

   }

   public void print(){
      System.out.println(this.toString());
   }
}
