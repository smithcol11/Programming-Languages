# Programming-Languages
A class I took at Portland State University had us working with a few different languages in some abstract ways. For this class we used Java, Prolog, and Haskell. The programs were different from anything I had done before. I used each language to accomplish similar tasks. First, I wrote a program in each language to construct arithmetic functions using 10 and the operators (+, -, *, /). The second set of programs was to create statements of expressions. For this, I needed to use hashtables in Java, and symbol tables in Prolog and Haskell.

## Code Snippets
> Java
```java
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
```

> Prolog
```prolog
% main function, call to execute the compounds
main :- 
  % compound 1
  C = compound([
    assign(var(x), num(7)),
    assign(var(y), num(4)),
    assign(var(z), num(1)),
    while(greater(var(y), num(1)), compound([
      assign(var(z), multiply(var(z), var(x))),
      assign(var(y), subtract(var(y), num(1)))
      ])),
    output(var(z))
  ]),
  prettyprint(C, 0),
  ST = s{},
  eval(C, ST, TT),

  nl,

  % compound 2
  C2 = compound([
        assign(var(x), num(6)),
        if(greater(var(x), num(3)), 
          assign(var(x), subtract(var(x), num(1))), 
          assign(var(x), add(var(x), num(1)))),
        output(var(x))
    ]),
  prettyprint(C2, 0),
  ST2 = s{},
  eval(C2, ST2, TT2).
```

> Haskell
```haskell
evalTable :: Table
evalTable = evalStat summing []

-- solves the symbol table for ifthen
evalTable2 :: Table
evalTable2 = evalStat ifthen []

-- this function calls If, evaluates based on x
ifthen :: Statement
ifthen = Compound[Assign(Var "x")(Num 7),
         If(Less(Var "x")(Num 10))(Assign(Var "x")(Multiply(Var "x")(Var "x")))(Assign(Var "x")(Num 0))]

-- this function sums numbers until a reaches zero
summing :: Statement
summing = Compound[Assign(Var "i")(Num 5),
       Assign(Var "a")(Num 3),
       Assign(Var "b")(Num 2),
       While(Greater(Var "i")(Num 0))(Compound[Assign(Var "a")(Add(Var "a")(Var "b")),Assign(Var "i")(Sub(Var "i")(Num 1))] )]

-- this is main, it operates as a main would
-- Everything is called and operated out of main
main :: IO ()
main = do
    putStrLn "\nEvaluation of summing: "
    print evalTable
    putStr "\nDisplay:"
    putStr (displayStat summing 0)
    putStrLn "\n\nEvaluation of ifthen: "
    print evalTable2
    putStr "\nDisplay:"
    putStr (displayStat ifthen 0)
```
