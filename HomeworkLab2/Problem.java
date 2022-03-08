package com.homework;

import java.util.Arrays;

public class Problem
{
    private Event[] events;
    private Room[] rooms;

    public Problem() {}

    public Problem(Event[] events, Room[] rooms)
    {
        if(hasSimilarEvents(events))
            System.exit(-1);
        this.events = events;
        this.rooms = rooms;
    }

    public Event[] getEvents()
    {
        return events;
    }

    public void setEvents(Event[] events)
    {
        this.events = events;
    }

    public Room[] getRooms()
    {
        return rooms;
    }

    public void setRooms(Room[] rooms)
    {
        this.rooms = rooms;
    }

    public void addEvent(Event event)
    {
        events = Arrays.copyOf(events, events.length + 1);
        events[events.length - 1] = event;
    }

    public void addRoom(Room room)
    {
        rooms = Arrays.copyOf(rooms, rooms.length + 1);
        rooms[rooms.length - 1] = room;
    }

    public Boolean hasSimilarEvents(Event[] events)
    {
        for (int firstEventIndex = 0; firstEventIndex < events.length - 1; firstEventIndex++)
        {
            for (int secondEventIndex = firstEventIndex + 1; secondEventIndex < events.length; secondEventIndex++)
            {
                if(events[firstEventIndex].equals(events[secondEventIndex]))
                {
                    System.out.println("Similar events found");
                    System.out.println("First event: " + events[firstEventIndex].toString());
                    System.out.println("Second event: " + events[secondEventIndex].toString());
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString()
    {
        return "Problem{" +
                "events=" + Arrays.toString(events) +
                ", rooms=" + Arrays.toString(rooms) +
                '}';
    }
}
