/*************************************
Colin Smith
CS 320
HW5
05/09/2021
*************************************/

import java.util.*;

// Statement for compounding
public class Compound extends Statement {
  Statement [] stat;

  // simply stores whole statements in array
  public Compound(Statement [] s) {
    this.stat = s;
  }
  // loops through to eval
  public void eval() {
    for(Statement temp : stat) {
      temp.eval();
    }
  }
  // loops through to print
  // used int nest, if we are nested, we need another tab, works no matter how many tabs in
  public void print(int nest) {
    for(Statement temp : stat) {
      if(nest == 1) System.out.print("\t");
      System.out.print("\t");
      temp.print(nest);
    }
  }
}