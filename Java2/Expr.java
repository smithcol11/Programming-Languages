/*************************************
Colin Smith
CS 320
HW5
05/09/2021
*************************************/

// abstract base class named Expr for expression
abstract class Expr {
  // abstract functions
  public abstract void display();
  public abstract int eval();
}

// subclass Value, this has a num which is an int
class Value extends Expr {
  int num;
  // constructor to pass an int in to be stored
  public Value(int num) {
    this.num = num;
  }
  
  // display the num stored in this.num
  public void display() {
    System.out.print(this.num);
  }

// simply returns the num for arithmetic operations below
  public int eval() {
    return this.num;
  }
}

// Variable class
class Var extends Expr {
  String var;
  // constructor to pass a string in to be stored
  Var(String var) {
    this.var = var;
  }
  
  // display the string
  public void display() {
    System.out.print(this.var);
  }

// simply returns the num for arithmetic operations below
  public int eval() {
    return main.hTable.get(this.var);
  }
}

// Addition subclass, hold two expression that would be
// either a Value, or another arithmetic operation.
class Add extends Expr {
  Expr a, b;

  // constructor with two expressions
  public Add(Expr x, Expr y) {
    this.a = x;
    this.b = y;
  }

  // displays parenthesis and the operation symbol
  public void display() {
    System.out.print("( ");
    a.display();
    System.out.print(" + ");
    b.display();
    System.out.print(" )");
  }

  // returns an int of the evaluation
  public int eval() {
    return (a.eval() + b.eval());
  }
}

// Subtraction subclass, hold two expression that would be
// either a Value, or another arithmetic operation.
class Subtract extends Expr {
  Expr a, b;

  // constructor with two expressions
  public Subtract(Expr x, Expr y) {
    this.a = x;
    this.b = y;
  }

  // displays parenthesis and the operation symbol
  public void display() {
    System.out.print("( ");
    a.display();
    System.out.print(" - ");
    b.display();
    System.out.print(" )");
  }

  // returns an int of the evaluation
  public int eval() {
    return (a.eval() - b.eval());
  }
}

// Multiplication subclass, hold two expression that would be
// either a Value, or another arithmetic operation.
class Multiply extends Expr {
  Expr a, b;

  // constructor with two expressions
  public Multiply(Expr x, Expr y) {
    this.a = x;
    this.b = y;
  }

  // displays parenthesis and the operation symbol
  public void display() {
    System.out.print("( ");
    a.display();
    System.out.print(" * ");
    b.display();
    System.out.print(" )");
  }

  // returns an int of the evaluation
  public int eval() {
    return (a.eval() * b.eval());
  }
}

// Division subclass, hold two expression that would be
// either a Value, or another arithmetic operation.
class Division extends Expr {
  Expr a, b;

  // constructor with two expressions
  public Division(Expr x, Expr y) {
    if(x.eval() % y.eval() != 0) {
      throw new ArithmeticException("Division by zero, or non-zero quotient");
    }
    else {
      this.a = x;
      this.b = y;
    }
  }

  // displays parenthesis and the operation symbol
  public void display() {
    System.out.print("( ");
    a.display();
    System.out.print(" / ");
    b.display();
    System.out.print(" )");
  }

  // returns an int of the evaluation
  // for division, if there is a remainder, -1 is returned
  // then the program displays error and exits
  public int eval() {
    int result = (a.eval() / b.eval());
    int remainder = (a.eval() % b.eval());
    return result;
  }
}

// expression which can determine and print an equal to statement.
class Equal extends Expr {
  Expr x, y;
  Equal(Expr x, Expr y) {
    this.x = x;
    this.y = y;
  }
  public void display() {
    System.out.print("(");
    x.display();
    System.out.print(" == ");
    y.display(); 
    System.out.print(") ");
  }
  public int eval() {
    if(x.eval() == y.eval()) return 1;
    else return 0;
  }
}

// expression which can determine and print a not-equal to statement.
class NotEqual extends Expr {
  Expr x, y;
  NotEqual(Expr x, Expr y) {
    this.x = x;
    this.y = y;
  }
  public void display() {
    System.out.print("(");
    x.display();
    System.out.print(" != ");
    y.display(); 
    System.out.print(") ");
  }
  public int eval() {
    if(x.eval() != y.eval()) return 1;
    else return 0;
  }
}

// expression which can determine and print a less than statement.
class Less extends Expr {
  Expr x, y;
  Less(Expr x, Expr y) {
    this.x = x;
    this.y = y;
  }
  public void display() {
    System.out.print("(");
    x.display();
    System.out.print(" < ");
    y.display(); 
    System.out.print(") ");
  }
  public int eval() {
    if(x.eval() < y.eval()) return 1;
    else return 0;
  }
}

// expression which can determine and print a greater than statement.
class Greater extends Expr {
  Expr x, y;
  Greater(Expr x, Expr y) {
    this.x = x;
    this.y = y;
  }
  public void display() {
    System.out.print("(");
    x.display();
    System.out.print(" > ");
    y.display(); 
    System.out.print(") ");
  }
  public int eval() {
    if(x.eval() > y.eval()) return 1;
    else return 0;
  }
}

/* main class and function, will be used to compount statements together
public class Main {
  public static void main(String[] args) {
    
  }
} */



