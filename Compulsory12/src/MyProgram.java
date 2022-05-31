

import com.example.Test;

public class MyProgram {
    private String name = "Ana";
    public int n;
    int[] numbers = new int[3];

    public MyProgram() {
    }

    @Test public static void t1()
    {
        throw new RuntimeException("Boom");
    }

    @Test public static void t2()
    {
        throw new RuntimeException("Crash");
    }

    @Test public static void t3()
    {
        System.out.println("Succsesfull method.");
    }

    @Test public static void t4()
    {
        System.out.println(51+22);
    }

    @Test public static void t5()
    {
        System.out.println("End!");
    }


}
