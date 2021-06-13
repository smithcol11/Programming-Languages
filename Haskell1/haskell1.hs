----------------------------------------------------
-- Colin Smith
-- CS320
-- HW4 - Haskell
-- April 25, 2021
-- Sources: https://www.cse.chalmers.se/edu/year/2018/course/TDA452/lectures/RecursiveDataTypes.html
----------------------------------------------------

-- setup the data Expr, which contains ten and all equations of expressions
data Expr = Num Int
          | Ten
          | Add Expr Expr
          | Sub Expr Expr
          | Mul Expr Expr
          | Div Expr Expr
          deriving Show

-- called from printAll, used to print the equation in proper format
-- assert: prints (Sub 10 10) = (10 - 10)
prints :: Expr -> String
prints Ten = "10"
prints (Add x y) = "(" ++ p x ++ " + " ++ p y ++ ")"
  where p = prints
prints (Sub x y) = "(" ++ p x ++ " - " ++ p y ++ ")"
  where p = prints
prints (Mul x y) = "(" ++ p x ++ " * " ++ p y ++ ")"
  where p = prints
prints (Div x y) = "(" ++ p x ++ " / " ++ p y ++ ")"
  where p = prints

-- these are the operations which are called by eval
ops :: [Expr -> Expr -> Expr]
ops = [Add, Sub, Mul, Div]

-- All evaluations are called here
-- assert: eval (Add 10 10) = 20
-- assert: eval (Mul 10 10) = 100
eval :: Expr -> Int
eval Ten = 10
eval (Add x y) = eval x + eval y
eval (Sub x y) = eval x - eval y
eval (Mul x y) = eval x * eval y
eval (Div x y) = if eval y /= 0 && eval x `mod` eval y == 0 then eval x `div` eval y else -1337000000

-- this is how I build the equations
-- assert: construct (3) = "all equations with 3 operations"
-- assert: construct (4) = "all equations with 4 operations"
construct :: (Eq ten, Num ten, Enum ten) => ten -> [Expr]
construct 0 = [Ten]
construct n = [op ea eb| op <- ops, x <- [0..n-1], y <- [n-1 - x], ea <- construct x, eb <- construct y]

-- prints all the equations using different ops with 10
printAll :: (Eq ten, Num ten, Enum ten) => ten -> IO ()
printAll n = mapM_ (print . prints) (construct n)

-- Checks if the result is 0-9
-- assert: filters (-2) = false
-- assert: filters (7) = true
filters :: Expr -> Bool
filters x = eval x >= 0 && eval x < 10

-- this is the function called by main, it will start the printing of all equations
-- and use a filter to display the right ones
-- assert: length (io (construct 3)) = 79
io :: [Expr] -> IO ()
io x = mapM_ print([(prints eq, eval eq) | eq <- x, filters eq])

-- Run main to print all equations and their results in the desired range
main = io (construct 3)