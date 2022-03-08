package com.homework;

public class Event
{
    private String name;
    private Integer size;
    private Integer start;
    private Integer end;

    public Event(String name, Integer size, Integer start, Integer end)
    {
        this.name = name;
        this.size = size;
        this.start = start;
        this.end = end;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getSize()
    {
        return size;
    }

    public void setSize(Integer size)
    {
        this.size = size;
    }

    public Integer getStart()
    {
        return start;
    }

    public void setStart(Integer start)
    {
        this.start = start;
    }

    public Integer getEnd()
    {
        return end;
    }

    public void setEnd(Integer end)
    {
        this.end = end;
    }

    @Override
    public String toString()
    {
        return "Event{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
