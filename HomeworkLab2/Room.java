package com.homework;

import java.util.Objects;

public abstract class Room
{
    private String name;
    private Integer capacity;

    public Room(String name, Integer capacity)
    {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getCapacity()
    {
        return capacity;
    }

    public void setCapacity(Integer capacity)
    {
        this.capacity = capacity;
    }

    @Override
    public String toString()
    {
        return "Room{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;
        return Objects.equals(name, room.name);
    }
}

