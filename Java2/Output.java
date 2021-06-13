/*************************************
Colin Smith
CS 320
HW5
05/09/2021
*************************************/

import java.util.*;

// Statement for outputing data in lists
public class Output extends Statement {
  Var vars;

  Output(Var v) {
    this.vars = v;
  }
  // gets from hashtable to determine what to print
  public void eval() {
    vars.display();
    System.out.print(" = " + main.hTable.get(vars.var));
  }
  // pretty print, uses nest again to determine if we are nested for printing
  public void print(int nest) {
    if(nest == 1) System.out.print("\t");
    System.out.print("print: ");
    vars.display();
  }
}