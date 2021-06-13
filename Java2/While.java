/*************************************
Colin Smith
CS 320
HW5
05/09/2021
*************************************/

import java.util.*;

// Statement for doing a while loop
public class While extends Statement {
  Expr expr;
  Statement stat;

  // takes statement and expression
  public While(Statement s, Expr e) {
    this.expr = e;
    this.stat = s;
  }
  // depending on if while is true, may run more than once
  public void eval() {
    while(expr.eval() == 1) {
      stat.eval();
    }
  }
  // pretty print, handles while and certain tabs
  public void print(int nest) {
    System.out.print("while ");
    expr.display();
    System.out.println("{");
    stat.print(1);
    System.out.print("\t");
    System.out.println("}");
  }
}