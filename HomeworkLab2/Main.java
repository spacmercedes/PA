package com.homework;

public class Main
{
    public static void main(String[] args)
    {
        Event[] events = initEvents();
        Room[] rooms = initRooms();
        Problem problem = generateProblem(events, rooms);
        Solution solution = new Solution();
        //solution.solveProblem(problem);
        System.out.println(solution.toString());
    }

    private static Event initEvent(String name, Integer size, Integer start, Integer end)
    {
        return new Event(name, size, start, end);
    }

    private static ComputerLab initComputerLab(String name, Integer capacity)
    {
        return new ComputerLab(name, capacity);
    }

    private static LectureHall initLectureHall(String name, Integer capacity, Boolean hasProjector)
    {
        return new LectureHall(name, capacity, hasProjector);
    }

    private static Event[] initEvents()
    {
        Event[] events = new Event[4];
        int index = 0;
        events[index++] = initEvent("C1", 100, 8, 10);
        events[index++] = initEvent("C2", 100, 10, 12);
        events[index++] = initEvent("L1", 30, 8, 10);
        events[index] = initEvent("L2", 30, 8, 10);
        return events;
    }

    private static Room[] initRooms()
    {
        Room[] rooms = new Room[3];
        int index = 0;
        rooms[index++] = initComputerLab("401", 30);
        rooms[index++] = initComputerLab("403", 30);
        rooms[index] = initComputerLab("405", 30);
        return rooms;
    }

    private static Problem generateProblem(Event[] events, Room[] rooms)
    {
        Problem problem = new Problem(events, rooms);
        problem.addEvent(initEvent("L3", 30, 10, 12));
        problem.addRoom(initLectureHall("309", 100, true));
        return problem;
    }
}
