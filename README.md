# Compiler
Optimized compiler for a language named YASL (Yet Another Simple Language) in Java. This includes development of lexical analyzer, semantic analyzer (parser), intermediate code generator and a target code generator. The lexical analyzer tokenizes the program; the parser constructs an abstract syntax tree (AST) of the program which can be used to generate the intermediate representation (IR) code


The Grammar for YASL:



<Program> -->
  program ID ; <Block> .

<Block> -->
  <ValDecls> <VarDecls> <FunDecls> <Stmt>

<ValDecls> -->
  <ValDecl> <ValDecls>
| ε

<ValDecl> -->
  val ID = <Sign> NUM ;

<Sign> -->
  -
| ε

<VarDecls> -->
  <VarDecl> <VarDecls>
| ε

<VarDecl> -->
  var ID : <Type> ;

<Type> -->
  int
| bool
| void

<FunDecls> -->
  <FunDecl> <FunDecls>
| ε

<FunDecl> -->
  fun ID ( <ParamList> ) : <Type> ; <Block> ;

<ParamList> -->
  <Params>
| ε

<Params> -->
  <Param> , <Params>
| <Param>

<Param> -->
  ID : <Type>

<StmtList> —>
  <Stmts>
| ε

<Stmts> -->
  <Stmt> ; <Stmts>
| <Stmt>

<Stmt> -->
  let ID = <Expr>
| begin <StmtList> end
| if <Expr> then <Stmt>
| if <Expr> then <Stmt> else <Stmt>
| while <Expr> do <Stmt>
| input STRING
| input STRING , ID
| print <Items>
| <Expr>

<Items> -->
  <Item> , <Items>
| <Item>

<Item> -->
  <Expr>
| STRING

<Expr> -->
  <SimpleExpr> <RelOp> <SimpleExpr>
| <SimpleExpr>

<RelOp> -->
  == | <> | <= | >= | < | >

<SimpleExpr> -->
  <SimpleExpr> <AddOp> <Term>
| <Term>

<AddOp> -->
  + | - | or

<Term> -->
  <Term> <MulOp> <Factor>
| <Factor>

<MulOp> -->
  * | div | mod | and

<Factor> -->
  NUM
| ID
| ID ( <ArgList> )
| true
| false
| <UnOp> <Factor>
| ( <Expr> )

<UnOp> -->
  - | not

<ArgList> -->
  <Args>
| ε

<Args> -->
  <Expr> , <Args>
| <Expr>
