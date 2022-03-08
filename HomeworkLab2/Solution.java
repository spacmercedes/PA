package com.homework;

import java.util.Arrays;

public class Solution
{
    private Room[] eventsAssignment;

    public Solution() {}
    public Room[] getAssignment()
    {
        return eventsAssignment;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "assignment=" + Arrays.toString(eventsAssignment) +
                '}';

    }}
