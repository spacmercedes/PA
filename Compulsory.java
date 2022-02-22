package com.example.compulsory;

public class Compulsory {

    public static void main(String[] args)
    {
        System.out.println("Hello World!"); //1
        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"}; //2
        int index = sumOfDigits(computeRandomNumber()); //6
        System.out.println("Willy-nilly, this semester I will learn " + languages[index]); //7
    }

    public static int computeRandomNumber() //3
    {
        int n = (int) (Math.random() * 1_000_000);
        n = (n * 3 + 0b10101 + 0xFF) * 6; //4
        return n;
    }

    public static int sumOfDigits(int n) //5
    {
        int sum = 0;

        while(true)
        {
            while(n != 0)
            {
                sum += n % 10;
                n = n / 10;
            }

            if(sum < 10)
            {
                return sum;
            }

            n = sum;
            sum = 0;
        }
    }
}