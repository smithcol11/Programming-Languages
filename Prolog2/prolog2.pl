% *******************************************************************
% Colin Smith
% CS 320
% HW6
% May 16th, 2021
% *******************************************************************

% base case eval
eval(num(X), X, _).

% next few evals are for different types of arithmetic
% addition
eval(add(X, Y), Z, ST) :- 
  eval(X, VX, ST), 
  eval(Y, VY, ST), 
  Z is VX + VY.

% subtraction
eval(subtract(X, Y), Z, ST) :- 
  eval(X, VX, ST), 
  eval(Y, VY, ST), 
  Z is VX - VY.

% multiplication
eval(multiply(X, Y), Z, ST) :- 
  eval(X, VX, ST), 
  eval(Y, VY, ST), 
  Z is VX * VY.

% division
eval(divide(X, Y), Z, ST) :- 
  eval(X, VX, ST), 
  eval(Y, VY, ST), 
  Z is VX / VY.

% eval with the variable X
eval(var(X), Z, ST) :-
  Z is ST.get(X).

% these next few evals are for comparisons
% check if equal to
eval(equal(X, Y), Z, ST) :- 
  eval(X, VX, ST), 
  eval(Y, VY, ST), 
  VX =:= VY -> Z is 1 ; Z is 0.

% check if not equal to
eval(notequal(X, Y), Z, ST) :- 
  eval(X, VX, ST), 
  eval(Y, VY, ST), 
  VX =:= VY -> Z is 0 ; Z is 1.

% check if greater than
eval(greater(X, Y), Z, ST) :- 
  eval(X, VX, ST), 
  eval(Y, VY, ST), 
  VX > VY -> Z is 1 ; Z is 0.

% check if less than
eval(less(X, Y), Z, ST) :- 
  eval(X, VX, ST), 
  eval(Y, VY, ST), 
  VX < VY -> Z is 1 ; Z is 0.

% grab output from symbol table ST
eval(output(var(V)), ST, ST) :- 
  write(V),
  write(' = '),
  write(ST.get(V)),
  nl.

% output V
eval(output(num(V)), ST, ST) :-
  write(V).

 % assign the eval to K, and put in ST 
eval(assign(var(V), E), ST, TT) :-
  eval(E, K, ST),
  TT = ST.put(V, K).

% H to T of compound, passing around ST
eval(compound([H|T]), ST, TT2) :-
  eval(H, ST, TT),
  eval(compound(T), TT, TT2).

% compound base case
eval(compound([]), ST, ST).

% eval the while loop
eval(while(E, S), ST, TT2):-
  eval(E, Z, ST),
  Z =:= 1 -> 
  eval(S, ST, TT),
  eval(while(E, S), TT, TT2) ;
  TT2 = ST.

% eval the if then else statement
eval(if(E, S, S2), ST, TT):-
  eval(E, Z, ST),
  Z =:= 1 -> 
  eval(S, ST, TT) ;
  eval(S2, ST, TT). 

% Tab function
tab(0).
tab(N) :-
  write("  "),
  K is N-1,
  tab(K).

% This is the start of all the pretty print functions
% print the current num X
prettyprint(num(X), _) :- 
  write(X).

% print the current var X
prettyprint(var(X), _) :- 
  write(X).

% print the addition of X Y
prettyprint(add(X, Y), _) :- 
  prettyprint(X, _), 
  write(' + '), 
  prettyprint(Y, _).

% print the subtraction of X Y
prettyprint(subtract(X, Y), _) :- 
  prettyprint(X, _), 
  write(' - '), 
  prettyprint(Y, _).

% print the multiplication of X Y
prettyprint(multiply(X, Y), _) :- 
  prettyprint(X, _), 
  write(' * '), 
  prettyprint(Y, _).

% print the division of X Y
prettyprint(divide(X, Y), _) :- 
  prettyprint(X, _), 
  write(' / '), 
  prettyprint(Y, _).

% print X == Y
prettyprint(equal(X, Y), _) :- 
  prettyprint(X, _),
  write(' == '), 
  prettyprint(Y, _).

% print X != Y
prettyprint(notequal(X, Y), _) :- 
  prettyprint(X, _), 
  write(' /= '), 
  prettyprint(Y, _).

% print X > Y
prettyprint(greater(X, Y), _) :- 
  prettyprint(X, _), 
  write(' > '), 
  prettyprint(Y, _).

% print X < Y
prettyprint(less(X, Y), _) :- 
  prettyprint(X, _), 
  write(' < '), 
  prettyprint(Y, _).

% print X := Y, assign the variable
prettyprint(assign(V, E), N) :-
  tab(N),
  prettyprint(V, N), 
  write(' := '), 
  prettyprint(E, N).

% print the compound, called from main, enters sub funcitons above
prettyprint(compound([]), _).
prettyprint(compound([H|T]), N) :- 
  prettyprint(H, N), 
  nl, 
  prettyprint(compound(T), N).

% print the output, accounting for tabs
prettyprint(output(V), N) :- 
  tab(N), 
  write("output "), 
  prettyprint(V, N).

% print the while loop with a tab
prettyprint(while(E, S), N) :- 
  tab(N), 
  write("while("), 
  prettyprint(E, N), 
  write(")"), 
  nl, 
  K is N+1, 
  prettyprint(S, K).

% print the if then else statement with a tab
prettyprint(if(E, S, S2), N) :- 
  tab(N), 
  write("if("), 
  prettyprint(E, N), 
  write("):"), 
  nl, 
  K is N+1, 
  prettyprint(S, K), 
  nl, 
  write("else:"), 
  nl, 
  prettyprint(S2, K).

%********************************************************************

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
  