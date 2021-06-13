/*************************************
Colin Smith
CS 320
HW5
05/09/2021
*************************************/

import java.util.*;

// Statement for if-then-else statements
public class If extends Statement {
  Expr expr;
  Statement stat1, stat2;

  // takes two statements and expression, one for if, other for else
  public If(Statement s1, Statement s2, Expr e) {
    this.expr = e;
    this.stat1 = s1;
    this.stat2 = s2;
  }
  // will run one code or the other, depending
  public void eval() {
    if(expr.eval() == 1) stat1.eval();
    else stat2.eval();
  }
  // pretty prints and handles if tabs
  public void print(int nest) {
    System.out.print("if ");
    expr.display();
    System.out.println("{");
    stat1.print(1);
    System.out.print("\t");
    System.out.println("}");
    System.out.print("\t");
    System.out.println("else {");
    stat2.print(1);
    System.out.print("\t");
    System.out.println("}");
  }
}