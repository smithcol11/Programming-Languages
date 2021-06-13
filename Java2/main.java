/*************************************
Colin Smith
CS 320
HW5
05/09/2021
*************************************/

import java.util.*;
// import hashmap, use hashtable instead
import java.util.HashMap;

// main class which constructs the statements, then prints them in pretty format
public class main {
  public static Hashtable<String, Integer> hTable = new Hashtable<String, Integer>();
  public static void main(String [] args) {
    // first compound, which uses a while loop with a raised to power inside
    Compound power = new Compound(new Statement[] {
      new Assign(new Var("x"), new Value(2)),
      new Assign(new Var("y"), new Value(3)),
      new Assign(new Var("z"), new Value(1)),
      new While(
        new Compound(new Statement[] {
          new Assign(new Var("z"), new Multiply(new Var("z"), new Var("x"))),
          new Assign(new Var("y"), new Subtract(new Var("y"), new Value(1)))
        }),
        new Greater(new Var("y"), new Value(0))
      ),
      new Output(new Var("z"))
    });
    // beginning of pretty print
    System.out.println("\n**************************************");
    System.out.println("\nCode: ");
    power.print(0);
    System.out.println("\n\nOutput: ");
    System.out.print("\t");
    power.eval();
    System.out.println();

    // second compound, tests if-else, with different equations
    Compound ifstat = new Compound(new Statement[] {
      new Assign(new Var("x"), new Value(2)),
      new Assign(new Var("y"), new Value(3)),
      new Assign(new Var("z"), new Value(1)),
      new If(
        new Compound(
          new Statement[] {
            new Assign(new Var("z"), new Add(new Var("z"), new Var("x")))
          }),
        new Compound(
          new Statement[] {
            new Assign(new Var("z"), new Subtract(new Var("z"), new Var("x")))
          }),
        new Equal(new Var("y"), new Value(7))),
      new Output(new Var("z"))
    });
    // begin pretty print
    System.out.println("\n**************************************");
    System.out.println("\nCode: ");
    ifstat.print(0);
    System.out.println("\n\nOutput: ");
    System.out.print("\t");
    ifstat.eval();
    System.out.println();
    System.out.println("\n**************************************\n");
  }

}