/*************************************
Colin Smith
CS 320
HW2
Test file for 'Main.java'
04/11/2021
*************************************/

// All imports needed to perform tests, as shown in lab
import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

// using one class for all tests
public class MainTest extends junit.framework.TestCase {
  
  // This is a block of expression initializations
  // These will be used throughout the tests to ensure they work
  Expr three = new Value(3);
  Expr six = new Value(6);
  Expr five = new Value(5);
  Expr zero = new Value(0);
  Expr two = new Value(2);
  Expr four = new Value(4);
  Expr ten = new Value(10);
  Expr nine = new Value(9);

  // tests to see if Value will hold the proper int
  @Test
  public void test_Value() {
    Expr test = new Value(2);
    assertEquals((2), test.eval());
  }

  // tests basic Addition
  @Test
  public void test_Add() {
    Expr test = new Add(six, new Add(two, four));
    assertEquals((6 + 2 + 4), test.eval());
  }

  // tests basic Subtraction
  @Test
  public void test_Subtract() {
    Expr test = new Subtract(ten, four);
    assertEquals((10 - 4), test.eval());
  }

  // tests basic Multiplication
  @Test
  public void test_Multiply() {
    Expr test = new Multiply(five, three);
    assertEquals((5 * 3), test.eval());
  }

  // tests basic Division
  @Test
  public void test_Divison() {
    Expr test = new Division(six, three);
    assertEquals((6 / 3), test.eval());
  }

  // tests division that fails due to whole numbers
  @Test
  public void test_DivisionFail() {
    try {
      Expr test = new Division(five, two);
    } catch(Exception e) {
      System.out.println(e);
    }
  }

  // test division that fails due to denominator of 0
  @Test
  public void test_DivisionZero() {
    try {
      Expr test = new Division(five, zero);
    } catch(Exception e) {
      System.out.println(e);
    }
  }

  // test a combo of functions
  @Test
  public void test_Combo1() {
    Expr test = new Add(five, new Multiply(three, new Subtract(six, three)));
    // this assert evaluates to 45
    assertEquals((5 + (3 * (6 - 3))), test.eval());
  }

  // test another combo of functions
  @Test
  public void test_Combo2() {
    Expr test = new Multiply(five, new Division(nine, new Subtract(six, three)));
    // this assert evaluates to 15
    assertEquals((5 * (9 / (6 - 3))), test.eval());
  }
}