package com.homework;

public class LectureHall extends Room
{
    private Boolean hasProjector;

    public LectureHall(String name, Integer capacity, Boolean hasProjector)
    {
        super(name, capacity);
        this.hasProjector = hasProjector;
    }

    public Boolean getHasProjector()
    {
        return hasProjector;
    }

    public void setHasProjector(Boolean hasProjector)
    {
        this.hasProjector = hasProjector;
    }

    public String toString()
    {
        return super.toString() +
                ", hasProjector=" + hasProjector +
                '}';
    }

}
