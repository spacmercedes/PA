package com.example;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

public class ChocoSolver {
    // 1. Create a Model
    Model model = new Model("Problem");
    // 2. Create variables
    IntVar sumOfPopulation = model.intVar("sum", 1, 100000,true);

    // 3. Post constraints

    // 4. Solve the problem

    // 5. Print the solution


}
