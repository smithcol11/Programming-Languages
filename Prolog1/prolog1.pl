% Colin Smith
% CS 320
% HW3 - Prolog
% April 19th, 2021

% base case expr, 10 with size one
expr(10, 1).

% this is the addition expression
expr(Y + Z, N) :- 
  predicate(Y, Z, N).

% subtraction expression
expr(Y - Z, N) :- 
  predicate(Y, Z, N).

% division expression
% also contains error checking for 0 and whole numbers
expr(Y / Z, N) :- 
  predicate(Y, Z, N), 
  A is Z, A > 0, 
  B is Y, 
  0 is mod(B, A).

% multiplication expression
expr(Y * Z, N) :- 
  predicate(Y, Z, N).

% This prints the expression and its result
exprSize(N) :- 
  expr(Y, N),
  A is Y,
  % result must be between 0-9
  between(0, 9, A),
  write(Y),
  write('='),
  write(A),
  nl.

% this is the predicate
predicate(Y, Z, N) :- 
    N > 1, 
    N_ is N-1,
    between(1, N_, A),
    between(1, N_, B), 
    expr(Y, A), 
    expr(Z, B), 
    N is A + B.

% main 'function' to call aggregate_all and print num of expressions
main :- 
  aggregate_all(count, exprSize(4), T),
  write(T), write(' number of expressions.').