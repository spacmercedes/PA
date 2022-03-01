package Homework1;

import java.util.Random;

public class Main
{
    private static final Random rand = new Random();

    public static void main(String[] args)
    {
        checkInput(args);
        int n = Integer.parseInt(args[0]);
        int p = Integer.parseInt(args[1]);
        int alphaLen = args.length - 2;
        char[] alphabet = new char[alphaLen];
        for (int i = 0; i < alphaLen; i++)
        {
            alphabet[i] = args[i + 2].charAt(0);
        }

        // For 10_000 it takes 7s
        // For 20_000 it takes 31s
        // For 30_000 out of heap space (4GB)
        if (n < 30_000)
        {
            String[] collection = getRandomWords(n, p, alphabet, alphaLen);  //Preiau argumentele si generez cuvintele
            System.out.println("Generated Words:");
            for (String word : collection)  //Pentru aranjarea output-ului
            {
                System.out.printf("%s ", word);
            }
            System.out.println();

            boolean[][] wordsRelations = generateWordsRelations(n, p, collection);   // Generez relatia booleana dintre cuvinte
            printRelationMatrix(n, p, collection, wordsRelations);                  // Afisez matricea de incidenta

            String[][] neighborsMatrix = getNeighbors(n, collection, wordsRelations); // Plasez vecinii intr-un array
            printNeighborsMatrix(n, collection, neighborsMatrix);   // Afisez vecinii fiecarui cuvant
        }
        else
        {                    //Calcularea timpului de executie in cazul in care n>30 000 si afisarea acestuia
            long sTime = System.nanoTime();
            String[] collection = getRandomWords(n, p, alphabet, alphaLen);
            boolean[][] wordsRelations = generateWordsRelations(n, p, collection);
            getNeighbors(n, collection, wordsRelations);
            long eTime = System.nanoTime();
            System.out.println((eTime - sTime) / Math.pow(10, 9)); // (Timpul de finalizare - Timpul de start)/10^9
        }
    }

    public static void checkInput(String[] args) // Validarea argumenetelor introduse la linia de comanda
    {
        if (args.length < 3)  // Numarul de argumente trebuie sa fie minim 3
        {
            System.out.println("Not enough arguments.");
            System.exit(-1);
        }
        try                                 // Verifica daca primele 2 argumente sunt integer
        {
            Integer.parseInt(args[0]);
            Integer.parseInt(args[1]);
        } catch (NumberFormatException e)
        {
            System.out.println("Invalid numeric argument(s).");
            System.exit(-1);
        }

        for (int i = 2; i < args.length; i++)
        {
            if (args[i].length() != 1)
            {
                System.out.println("Invalid argument(s) length."); // Lungimea argumentului
                System.exit(-1);
            }
            if (!Character.isUpperCase(args[i].charAt(0))) // Verificarea daca e char si uppercase
            {
                System.out.println("Invalid character argument(s).");
                System.exit(-1);
            }
        }
    }

    public static String[] getRandomWords(int n, int p, char[] alphabet, int alphaLen) //Generarea cuvintelor random
    {
        String[] collection = new String[n];
        for (int i = 0; i < n; i++)
        {
            StringBuilder word = new StringBuilder();
            for (int j = 0; j < p; j++)
            {
                int k = rand.nextInt(alphaLen);
                word.append(alphabet[k]);
            }
            collection[i] = word.toString();
        }
        return collection;
    }

    public static boolean[][] generateWordsRelations(int n, int p, String[] collection)// Gasirea adiacentei intre cuvinte
    {
        boolean[][] wordsRelations = new boolean[n][n]; //Creez matricea nxn
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                wordsRelations[i][j] = false; // initializez matricea cu false
            }
        }

        for (int i = 0; i < n - 1; i++)  // primul cuvant i, pana la n-1, deoarece verificarea are loc mereu cu al doilea cuvant
        {
            for (int j = i + 1; j < n; j++) //al doilea cuvant i+1 care va fi comparat cu al doilea
            {
                boolean isNeighbors = false; // initializez cu false
                for (int k = 0; k < p; k++) //fiecare litera k din primul cuvant
                {
                    for (int l = 0; l < p; l++) //fiecare litera l din al doilea cuvant
                    {
                        if (collection[i].charAt(k) == collection[j].charAt(l)) // caracterul la poz k din cuv i = caracterul l din cuv j
                        {
                            wordsRelations[i][j] = true;
                            wordsRelations[j][i] = true;
                            isNeighbors = true; //gaseste un vecin
                            break; //face break la al doilea cuvant
                        }
                    }
                    if (isNeighbors)
                    {
                        break;
                    }
                }
            }
        }
        return wordsRelations;
    }

    public static void printRelationMatrix(int n, int p, String[] collection, boolean[][] wordsRelations) //Afisarea Matricei de adiacenta
    {
        System.out.println("Words Relations:");
        for (int i = 0; i < p + 1; i++)
        {
            System.out.print(' ');
        }
                                        //Afisarea cuvintelor pe rand/coloane
        for (String word : collection)
        {
            System.out.printf("%s ", word);
        }
        System.out.println();

        for (int i = 0; i < n; i++)    // Afisarea matricii
        {
            System.out.printf("%s ", collection[i]);
            for (int j = 0; j < n; j++)
            {
                System.out.printf("%d", wordsRelations[i][j] ? 1 : 0); // if wordRelation = true => 1, else => 0
                for (int k = 0; k < p; k++) //  Spatii libere, pentru lizibilitate
                {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }

    public static String[][] getNeighbors(int n, String[] collection, boolean[][] wordsRelations) //Gasirea vecinilor
    {
        String[][] neighborsMatrix = new String[n][n];

        for (int i = 0; i < n; i++) // parcurg coloanele matricii
        {
            int index = 0;  // pe ce pozitie voi pune cuvantul
            for (int j = 0; j < n; j++) //parcurg randurile
            {
                if (wordsRelations[i][j])
                {
                    neighborsMatrix[i][index] = collection[j]; // if true, pun in array cuvantul j
                    index++;
                }
            }
        }
        return neighborsMatrix;
    }

    public static void printNeighborsMatrix(int n, String[] collection, String[][] neighborsMatrix) // Afisarea vecinilor fiecarui cuvant
    {
        System.out.println("Words Relations:");
        for (int i = 0; i < n; i++)
        {
            System.out.printf("%s: ", collection[i]);
            for (int j = 0; j < n; j++)
            {
                if (neighborsMatrix[i][j] != null)
                {
                    System.out.printf("%s ", neighborsMatrix[i][j]); //afiseaza vecinii
                }
            }
            System.out.println();
        }
    }
}
