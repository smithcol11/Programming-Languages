/*************************************
Colin Smith
CS 320
HW5
05/09/2021
*************************************/

import java.util.*;

// Statement for Assigning
public class Assign extends Statement {
  Expr expr;
  Var vars;

  // set up assign, and add to hashtable
  public Assign(Var v, Expr e) {
    this.vars = v;
    this.expr = e;
    main.hTable.put(vars.var, expr.eval());
  }
  public void eval() {
    main.hTable.put(vars.var, expr.eval());
  }
  // will print the var and an =, allowing setting
  public void print(int nest) {
    System.out.print(vars.var + " = ");
    expr.display();
    System.out.println();
  }
}