----------------------------------------------------
-- Colin Smith
-- CS320
-- HW5 - Haskell
-- May 24, 2021
-- Sources: None
----------------------------------------------------

-- setup expression and all types that can be created
data Expr = Num Int
          | Var String
          | Add Expr Expr
          | Sub Expr Expr
          | Multiply Expr Expr
          | Divide Expr Expr
          | Equal Expr Expr
          | NotEqual Expr Expr
          | Less Expr Expr
          | Greater Expr Expr
          deriving Show 

-- setup statements and define all types of statments
data Statement = Compound [Statement]
               | If Expr Statement Statement
               | While Expr Statement
               | Assign Expr Expr
               deriving Show

-- introducting the symbol table
-- takes a string and an int, one for the content, and the other for the key
type Table = [(String, Int)]
-- got this table code from lab, this is used to get from the Table
get :: Table -> String -> Int
get ((k,v):st) key = if k == key then v else get st key
-- also got this code from lab, this is used to set the table values
set :: Table -> String -> Int -> Table
set [] s i = [(s,i)]
set ((k,v):st) key value = if k == key then (key,value):st else (k,v):set st key value

-- eval, this contains all evaluation including comparisons
eval :: Expr -> Table -> Int
eval (Num x) table = x
eval (Var string) table = get table string
eval (Add x y) table = eval x table + eval y table
eval (Sub x y) table = eval x table - eval y table
eval (Multiply x y) table = eval x table * eval y table
eval (Divide x y) table = eval x table `div` eval y table
eval (Equal x y) table = if eval x table == eval y table then 1 else 0
eval (NotEqual x y) table = if eval x table /= eval y table then 1 else 0
eval (Less x y) table = if eval x table < eval y table then 1 else 0
eval (Greater x y) table = if eval x table > eval y table then 1 else 0

-- this eval handles statments, so these will end up calling the above evals
evalStat :: Statement -> Table -> Table
evalStat (If expr stat1 stat2) table = if eval expr table == 1 then evalStat stat1 table else evalStat stat2 table
evalStat (While expr stat) table = if eval expr table == 1 then evalStat (While expr stat) (evalStat stat table) else table
evalStat (Assign (Var expr1) expr2) table = set table expr1 (eval expr2 table)
evalStat (Compound (stat:statList)) table = evalStat (Compound statList) (evalStat stat table)
evalStat (Compound []) table = table

-- I call my prettyPrint, display
-- this will display all evals and comparisons in a nice format
display :: Expr -> String
display (Num x) = show x
display (Var x) = x
display (Add x y) = "(" ++ display x ++ " + " ++ display y ++ ")"
display (Sub x y) = "(" ++ display x ++ " - " ++ display y ++ ")"
display (Multiply x y) = "(" ++ display x ++ " * " ++ display y ++ ")"
display (Divide x y) = "(" ++ display x ++ " / " ++ display y ++ ")"
display (Equal x y) = "(" ++ display x ++ " == " ++ display y ++ ")"
display (NotEqual x y) = "(" ++ display x ++ " != " ++ display y ++ ")"
display (Less x y) = "(" ++ display x ++ " < " ++ display y ++ ")"
display (Greater x y) = "(" ++ display x ++ " > " ++ display y ++ ")"

-- this will prettyPrint the statements themselves, such as if or While
displayStat :: Statement -> Int -> String
displayStat (If expr stat1 stat2) x = "\n" ++ tab x ++ "if" ++ display expr ++ tab x ++ displayStat stat1 (x + 1) ++ "\nelse" ++ displayStat stat2 (x + 1)
displayStat (While expr stat) x = "\n" ++ tab x ++ "while" ++ display expr ++ tab x ++ displayStat stat (x + 1)
displayStat (Assign expr1 expr2) x = "\n" ++ tab x ++ display expr1 ++ " = " ++ display expr2
displayStat (Compound (stat:statList)) x = tab x ++ displayStat stat x ++ tab x ++ displayStat (Compound statList) x
displayStat (Compound[]) x = ""

-- similar to my last program, I use a tab function to know if I need to tab in or not
tab :: Int -> String
tab 0 = ""
tab x = "  " ++ tab(x - 1)

-- here we build or make summing, I use putStr at this was the only way to get '\n' working
construct :: Statement -> IO ()
construct summing = putStr(displayStat summing 0)

-- this will solve the symbol table of summing
evalTable :: Table
evalTable = evalStat summing []

-- solves the symbol table for ifthen
evalTable2 :: Table
evalTable2 = evalStat ifthen []

-- this function calls If, evaluates based on x
ifthen :: Statement
ifthen = Compound[Assign(Var "x")(Num 7),
         If(Less(Var "x")(Num 10))(Assign(Var "x")(Multiply(Var "x")(Var "x")))(Assign(Var "x")(Num 0))]

-- this function sums numbers until a reaches zero
summing :: Statement
summing = Compound[Assign(Var "i")(Num 5),
       Assign(Var "a")(Num 3),
       Assign(Var "b")(Num 2),
       While(Greater(Var "i")(Num 0))(Compound[Assign(Var "a")(Add(Var "a")(Var "b")),Assign(Var "i")(Sub(Var "i")(Num 1))] )]

-- this is main, it operates as a main would
-- Everything is called and operated out of main
main :: IO ()
main = do
    putStrLn "\nEvaluation of summing: "
    print evalTable
    putStr "\nDisplay:"
    putStr (displayStat summing 0)
    putStrLn "\n\nEvaluation of ifthen: "
    print evalTable2
    putStr "\nDisplay:"
    putStr (displayStat ifthen 0)
