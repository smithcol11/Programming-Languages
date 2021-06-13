/*************************************
Colin Smith
CS 320
HW2
Finds and prints the 79 expresssions that are 0-9
04/11/2021
*************************************/

// Import utilities in order to use Vector class
import java.util.*;

// OfInterest class to hold all work
public class OfInterest {
  // only use one function, main, in order to call all necessary functions.
  public static void main(String[] args) {
    // Use a variety of declared vectors
    // in retrospect this could be shorter and optimized
    Vector <Expr> Left = new Vector <> ();
    Vector <Expr> Right = new Vector <> ();
    Vector <Expr> Temp = new Vector <> ();
    Vector <Expr> Total = new Vector <> ();
    
    // ten which equals 10
    // count will be used to print out the 79 values
    Expr ten = new Value(10);
    int count = 1;
 
    // this Temp ends up with (10 + 10)
    Temp.add(new Add(ten, ten));
    Temp.add(new Subtract(ten, ten));
    Temp.add(new Multiply(ten, ten));
    // Every invocation of division, 
    // there is a try to catch the error
    // but continue the program
    try {
      Temp.add(new Division(ten, ten));
    }
    catch(Exception e) {}

    // Right is then populated with ten and Temp
    // this gives 10 + (10 + 10)
    for(Expr temp : Temp) {
      Right.add(new Add(ten, temp));
      Right.add(new Subtract(ten, temp));
      Right.add(new Multiply(ten, temp));
      try {
        Right.add(new Division(ten, temp));
      }
      catch(Exception e) {}
    }

    // Finally this is added to Total
    // On the left is ten, and Right on the Right
    // this becomes (10 + (10 + (10 + 10)))
    for(Expr expr : Right) {
      Total.add(new Add(ten, expr));
      Total.add(new Subtract(ten, expr));
      Total.add(new Multiply(ten, expr));
      try {
      Total.add(new Division(ten, expr));
      }
      catch(Exception e) {}
    }

    // Then the opposite from above is done
    // This yields ((10 + (10 + 10)) + 10)
    for(Expr expr : Right) {
      Total.add(new Add(expr, ten));
      Total.add(new Subtract(expr, ten));
      Total.add(new Multiply(expr, ten));
      try {
        Total.add(new Division(expr, ten));
      }
      catch(Exception e) {}
    }

    // Vectors can be deleted with clear
    Right.clear();

    // Not I use the same setup for Temp
    // This time temp will be on the left
    // This gives ((10 + 10) + 10)
    for(Expr temp : Temp) {
      Right.add(new Add(temp, ten));
      Right.add(new Subtract(temp, ten));
      Right.add(new Multiply(temp, ten));
      try {
      Right.add(new Division(temp, ten));
      }
      catch(Exception e) {}
    }

    // Right is added to expression
    // This yields (10 + ((10 + 10) + 10)
    for(Expr expr : Right) {
      Total.add(new Add(ten, expr));
      Total.add(new Subtract(ten, expr));
      Total.add(new Multiply(ten, expr));
      try {
      Total.add(new Division(ten, expr));
      }
      catch(Exception e) {}
    }

    // Then this is reversed
    // This gives (((10 + 10) + 10) + 10)
    for(Expr expr : Right) {
      Total.add(new Add(expr, ten));
      Total.add(new Subtract(expr, ten));
      Total.add(new Multiply(expr, ten));
      try {
        Total.add(new Division(expr, ten));
      }
      catch(Exception e) {}
    }

    // Right is cleared again to populate it a final time
    Right.clear();

    // Left then holds (10 + 10)
    Left.add(new Add(ten, ten));
    Left.add(new Subtract(ten, ten));
    Left.add(new Multiply(ten, ten));
    try {
      Left.add(new Division(ten, ten));
    }
    catch(Exception e) {}

    // Right holds (10 + 10)
    Right.add(new Add(ten, ten));
    Right.add(new Subtract(ten, ten));
    Right.add(new Multiply(ten, ten));
    try {
      Right.add(new Division(ten, ten));
    }
    catch(Exception e) {}

    // Finally, Right and Left are added
    // This gives ((10 + 10) + (10 + 10))
    for (Expr expr : Left) {
      for (Expr expr2 : Right) {
        Total.add(new Add(expr, expr2));
        Total.add(new Subtract(expr, expr2));
        Total.add(new Multiply(expr, expr2));
        try {
          Total.add(new Division(expr, expr2));
        }
        catch(Exception e) {}
      }
    }
    
    // For loop to print the results of Total
    // The expressions are only printed if
    // they are 0 - 9
    //
    // when the if block is removed, it doesn't print 
    // 320 results, this is because I don't store invalid operations
    for(Expr expr : Total) {
      if(expr.eval() >= 0 && expr.eval() <= 9) {
        System.out.print(count + ": ");
        expr.display();
        System.out.println(" = " + expr.eval());
        count++;
      }
    }
  }
}