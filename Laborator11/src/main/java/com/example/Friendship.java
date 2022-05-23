package com.example;

public class Friendship {

    private int id ;
    private String person1;
    private String person2;


    public Friendship(int id, String person1, String person2) {
        this.id = id;
        this.person1 = person1;
        this.person2 = person2;
    }

public Friendship(){

}
    public void setId(int id) {
        this.id = id;
    }

    public void setPerson1(String person1) {
        this.person1 = person1;
    }

    public void setPerson2(String person2) {
        this.person2 = person2;
    }

    public int getId() {
        return id;
    }

    public String getPerson1() {
        return person1;
    }

    public String getPerson2() {
        return person2;
    }
}
