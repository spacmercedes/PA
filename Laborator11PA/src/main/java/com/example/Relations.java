package com.example;

public class Relations {
    private int id;
    private String person1;
    private String person2;

    public Relations(int id, String person1, String person2) {
        this.id = id;
        this.person1 = person1;
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
