package lexicalAnalysis;

import java.util.ArrayList;
import java.util.List;

public class RecursiveParser {

	private Scanner scanner;
	private Token current;
	
	public RecursiveParser(Scanner scanner) {
		this.scanner = scanner;
		this.current = scanner.next();
	}

	/**
	 * If the current token matches the given type, return it and advance to the
	 * next token; otherwise print an error message and exit. Harsh.
	 * 
	 * @param type
	 */
	private Token match(TokenType type) {
		if (current.type == type) {
			Token result = current;
			current = scanner.next();
			return result;
		} else {
			System.err.println("Expected " + type + " but found " + current);
			System.exit(1);
			return null; // never reaches this
		}
	}

	/**
	 * Check whether the current token's type is one of a given list of type.
	 * 
	 * @param types a variable number of arguments listing the expected token types
	 * @return true if the current token type is in the given list
	 */
	private boolean check(TokenType... types) {
		for (TokenType type : types) {
			if (current.type == type) {
				return true;
			}
		}
		return false;
	}
	

	
	
	
	public Program parseProgram() { 
				match(TokenType.PROGRAM);
				String name = match(TokenType.ID).lexeme;
				match(TokenType.SEMI);
				Block block = ParseBlock();
				match(TokenType.PERIOD);
				match(TokenType.EOF);

				return (new Program(name, block));
				
				
				
		}
		
	
	
	public Block ParseBlock() {
		List<ValDecl> val = ParseValDecls();
		List<VarDecl> var = ParseVarDecls();
		List<funDecl> fun = ParseFunDecls();
		stmt body = ParseStmt();
		
		return (new Block(val,var,fun, body));
	}
	
	
	public stmt ParseStmt() {
		if(check(TokenType.LET)) {
			match(TokenType.LET);
			String name = match(TokenType.ID).lexeme;
			match(TokenType.ASSIGN);
			Expr expr = ParseExpr();
			return (new Assign(name, expr));
		}
		else if (check(TokenType.BEGIN)) {
			match(TokenType.BEGIN);
			List<stmt> ss = parseStmtList();
			match(TokenType.END);
			return new Sequence(ss);
		} else if (check(TokenType.IF)) {
			match(TokenType.IF);
			Expr test = ParseExpr();
			match(TokenType.THEN);
			stmt s1 = ParseStmt();
			return parseStmtRest(test, s1);
		} else if (check(TokenType.WHILE)) {
			match(TokenType.WHILE);
			Expr test = ParseExpr();
			match(TokenType.DO);
			stmt s1 = ParseStmt();
			return new While(test, s1);
		} else if (check(TokenType.INPUT)) {
			match(TokenType.INPUT);
			String msg = match(TokenType.STRING).lexeme;
			return parseStmtRest2(msg);
		} else if (check(TokenType.PRINT)) {
			match(TokenType.PRINT);
			List<Item> list = parseItems();
			return new Print(list);
		} else if (check(TokenType.NUM, TokenType.ID, TokenType.TRUE, TokenType.FALSE, 
				         TokenType.MINUS, TokenType.NOT, TokenType.LPAREN)) {
			Expr expr = ParseExpr();
			return new ExprStmt(expr);
		} else {
			System.err.println("Error: Invalid Expression statement " + current);
			System.exit(1);
		}
		
		return null;
		
	}
	
	
	private List<Item> parseItems() {
		Item it = parseItem();
		return parseItemRest(it);
	}
	
	
	private List<Item> parseItemRest(Item it) {
		if (check(TokenType.COMMA)) {
			match(TokenType.COMMA);
			List<Item> its = parseItems();
			its.add(0, it);
			return its;
		} else {
			List<Item> its = new ArrayList<Item>();
			its.add(it);
			return its;
		}
	}
	
	
	private Item parseItem() {
		if (check(TokenType.STRING)) {
			String msg = match(TokenType.STRING).lexeme;
			return new StringItem(msg);
		} else if (check(TokenType.NUM, TokenType.ID, TokenType.TRUE, TokenType.FALSE, 
		                 TokenType.MINUS, TokenType.NOT, TokenType.LPAREN)) {
			Expr expr = ParseExpr();
			return new ExprItem(expr);
		} else {
			System.err.println("Invalid item: " + current);
			System.exit(1);
		}
		return null;
	}
	
	
	
	private stmt parseStmtRest(Expr test, stmt s1) {
		if (check(TokenType.ELSE)) {
			match(TokenType.ELSE);
			stmt s2 = ParseStmt();
			return new IfThenElse(test, s1, s2);
		} else {
			return new IfThen(test, s1);
		}
	}
	
	
	private stmt parseStmtRest2(String msg) {
		if (check(TokenType.COMMA)) {
			match(TokenType.COMMA);
			String id = match(TokenType.ID).lexeme;
			return new Input2(msg, id);
		} else {
			return new Input(msg);
		}
	}
	
	
	
	private List<stmt> parseStmtList() {
		if (check(TokenType.LET, TokenType.BEGIN, TokenType.IF, TokenType.WHILE, TokenType.INPUT, 
				  TokenType.PRINT, TokenType.NUM, TokenType.ID, TokenType.TRUE, TokenType.FALSE, 
				  TokenType.MINUS, TokenType.NOT, TokenType.LPAREN)) {
			return parseStmts();
		} else {
			return new ArrayList<stmt>();
		}
	}
	
	
	private List<stmt> parseStmts() {
		stmt s = ParseStmt();
		return parseStmtRest(s);
	}
	
	
	private List<stmt> parseStmtRest(stmt s) {
		if (check(TokenType.SEMI)) {
			match(TokenType.SEMI);
			List<stmt> ss = parseStmts();
			ss.add(0, s);
			return ss;
		} else {
			List<stmt> ss = new ArrayList<stmt>();
			ss.add(s);
			return ss;
		}
	}

	
	private Expr ParseExpr() {
		Expr left = parseSimpleExpr();
		return parseExprRest(left);
	}
	
	
	private Expr parseExprRest(Expr left) {
		if (check(TokenType.EQUAL, TokenType.NOTEQUAL, TokenType.LESSEQUAL, TokenType.GREATEREQUAL, TokenType.LESS, TokenType.GREATER)) {
			Op2 op = parseRelOp();
			Expr right = parseSimpleExpr();
			return new BinOp(left, op, right);
		} else {
			return left;
		}
	}
	

	private Expr parseSimpleExpr() {
		Expr leftT = parseTerm();
		while(check(TokenType.PLUS, TokenType.MINUS, TokenType.OR)) {
			Op2 op = parseAddOp();
			Expr rightT = parseTerm();
			leftT = new BinOp(leftT, op, rightT);
		}
		return leftT;
	}
	 Op2 parseRelOp() {
		if (check(TokenType.EQUAL)) {
			match(TokenType.EQUAL);
			return Op2.EQ;
		} else if (check(TokenType.NOTEQUAL)) {
			match(TokenType.NOTEQUAL);
			return Op2.NE;
		} else if (check(TokenType.LESSEQUAL)) {
			match(TokenType.LESSEQUAL);
			return Op2.LE;
		} else if (check(TokenType.GREATEREQUAL)) {
			match(TokenType.GREATEREQUAL);
			return Op2.GE;
		} else if (check(TokenType.LESS)) {
			match(TokenType.LESS);
			return Op2.LT;
		} else if (check(TokenType.GREATER)) {
			match(TokenType.GREATER);
			return Op2.GT;
		} else {
			System.err.println("Error: Invalid Operation statement " + current);
			System.exit(1);
		}
		return null; 
	}
	
	
	private Op2 parseAddOp() {
		if (check(TokenType.PLUS)) {
			match(TokenType.PLUS);
			return Op2.Plus;
		} else if (check(TokenType.MINUS)) {
			match(TokenType.MINUS);
			return Op2.Minus;
		} else if (check(TokenType.OR)) {
			match(TokenType.OR);
			return Op2.Or;
		} else {
			System.err.println("Invalid addition operator: " + current);
			System.exit(1);
		}
		return null; // unreachable
	}
	

	private Op2 parseMulOp() {
		if (check(TokenType.STAR)) {
			match(TokenType.STAR);
			return Op2.Times;
		} else if (check(TokenType.DIV)) {
			match(TokenType.DIV);
			return Op2.Div;
		} else if (check(TokenType.MOD)) {
			match(TokenType.MOD);
			return Op2.Mod;
		} else {
			System.err.println("Invalid multiplication operator: " + current);
			System.exit(1);
		}
		return null; 
	}
	
	
	private Op1 parseUnOp() {
		if (check(TokenType.MINUS)) {
			match(TokenType.MINUS);
			return Op1.Neg;
		} else if (check(TokenType.NOT)) {
			match(TokenType.NOT);
			return Op1.Not;
		} else {
			System.err.println("Invalid unary operator: " + current);
			System.exit(1);
		}
		return null;
	}
	
	
	private Expr parseTerm() {
		Expr leftF = parseFactor();
		while(check(TokenType.STAR, TokenType.DIV, TokenType.MOD, TokenType.AND)) {
			Op2 op = parseMulOp();
			Expr rightF = parseFactor();
			leftF = new BinOp(leftF, op, rightF);
		}
		return leftF;
	}
	
	    
	
	private Expr parseFactor() {
		if (check(TokenType.NUM)) {
			int value = Integer.parseInt(match(TokenType.NUM).lexeme);
			return new Num(value);
		} else if (check(TokenType.ID)) {
			String id = match(TokenType.ID).lexeme;
			return parseFactorRest(id);
		} else if (check(TokenType.TRUE)) {
			match(TokenType.TRUE);
			return new True();
		} else if (check(TokenType.FALSE)) {
			match(TokenType.FALSE);
			return new False();
		} else if (check(TokenType.MINUS, TokenType.NOT)) {
			Op1 op = parseUnOp();
			Expr expr = parseFactor();
			return new UnOp(op, expr);
		} else if (check(TokenType.LPAREN)) {
			match(TokenType.LPAREN);
			Expr expr = ParseExpr();
			match(TokenType.RPAREN);
			return expr;
		} else {
			System.err.println("Invalid factor: " + current);
			System.exit(1);
		}
		return null; 
	}
	
	private Expr parseFactorRest(String id) {
		if (check(TokenType.LPAREN)) {
			match(TokenType.LPAREN);
			List<Expr> args = parseArgList();
			match(TokenType.RPAREN);
			return new Call(id, args);
		} else {
			return new Id(id);
		}
	}
	
	
	private List<Expr> parseArgList() {
		if (check(TokenType.NUM, TokenType.ID, TokenType.TRUE, TokenType.FALSE, TokenType.MINUS, TokenType.NOT, TokenType.LPAREN)) {
			return parseArgs();
		} else {
			return new ArrayList<Expr>();
		}
	}
	
	
	private List<Expr> parseArgs() {
		Expr a = ParseExpr();
		return parseArgsRest(a);
	}
	
	
	private List<Expr> parseArgsRest(Expr a) {
		if (check(TokenType.COMMA)) {
			match(TokenType.COMMA);
			List<Expr> as = parseArgs();
			as.add(0, a);
			return as;
		} else {
			List<Expr> args = new ArrayList<Expr>();
			args.add(a);
			return args;
		}
	}

	
	
	
	public List<ValDecl> ParseValDecls() {
		ArrayList<ValDecl> valDecList = new ArrayList<ValDecl>();
		while(check(TokenType.VAL)) {
			valDecList.add(ParseValDecl());
		}
		return valDecList;
	}	
	
	public ValDecl ParseValDecl(){
		match(TokenType.VAL);
		String name =match(TokenType.ID).lexeme;
		match(TokenType.ASSIGN);
		
		int temp = Sign() * Integer.parseInt(match(TokenType.NUM).lexeme);
		match(TokenType.SEMI);
		return (new ValDecl(name,temp));
	}
	
	public int Sign() {
		if(check(TokenType.MINUS)){
			match(TokenType.MINUS);
			return -1;
		}
		else {
			return 1;
		}
	}
	
	public List<VarDecl> ParseVarDecls(){
		ArrayList<VarDecl> varDecList = new ArrayList<VarDecl>();
		while(check(TokenType.VAR)) {
			varDecList.add(ParseVarDecl());
		}
		return varDecList;
	}
	
	public VarDecl ParseVarDecl(){
		match(TokenType.VAR);
		String name =match(TokenType.ID).lexeme;
		match(TokenType.COLON);
		Type type = null;
		
		if(check(TokenType.INT)) {
		type = Type.Int;
		match(TokenType.INT);
		}
		else if(check(TokenType.BOOL)) {
		type = Type.Bool;
		match(TokenType.BOOL);
		}
		else if(check(TokenType.VOID)) {
		type = Type.Void;
		match(TokenType.VOID);
		}
		else {
			System.err.println("Expected a variable type (Void|int|bool)"  + " but found " + current);
			System.exit(1);
		}
		match(TokenType.SEMI);
		
		
		return new VarDecl(name,type);
	}
	
	public List<funDecl> ParseFunDecls(){
		ArrayList<funDecl> fun = new ArrayList<funDecl>();
		while(check(TokenType.FUN)) {
			fun.add(ParseFunDecl());
		}
		
		return fun;
	}
	
	
	public funDecl ParseFunDecl() {
		
		match(TokenType.FUN);
		String id = match(TokenType.ID).lexeme;
		match(TokenType.LPAREN);
		List<Param> list = null;
		
		if(check(TokenType.RPAREN)) {
			match(TokenType.RPAREN);
		}
		else {
			
			list = ParseParam();
			
			
		}
		
		match(TokenType.COLON);
		Type type = null;
		if(check(TokenType.INT)) {
		type = Type.Int;
		match(TokenType.INT);
		}
		else if(check(TokenType.BOOL)) {
		type = Type.Bool;
		match(TokenType.BOOL);
		}
		else if(check(TokenType.VOID)) {
		type = Type.Void;
		match(TokenType.VOID);
		}
		else {
			System.err.println("Expected a variable type (Void|int|bool)"  + " but found " + current);
			System.exit(1);
		}
		match(TokenType.SEMI);
		
		Block block = ParseBlock();
		
		match(TokenType.SEMI);
		return(new funDecl(id,type,list, block));
		
		
	}
	
	public List<Param> ParseParam(){
		ArrayList<Param> p = new ArrayList<Param>();
		while(check(TokenType.ID)) {
			
			p.add(ParseParams());
			
		}
		
		return p;
	}
	
	public Param ParseParams() {
		
		String name = match(TokenType.ID).lexeme;
		
		match(TokenType.COLON);
		Type type = null;
		
		if(check(TokenType.INT)) {
		type = Type.Int;
		match(TokenType.INT);
		}
		else if(check(TokenType.BOOL)) {
		type = Type.Bool;
		match(TokenType.BOOL);
		}
		else if(check(TokenType.VOID)) {
		type = Type.Void;
		match(TokenType.VOID);
		}
		else {
			System.err.println("Expected a variable type (Void|int|bool)"  + " but found " + current);
			System.exit(1);
		}
		if(check(TokenType.RPAREN)) {
			match(TokenType.RPAREN);}
		else {
			match(TokenType.COMMA);
		}
		return (new Param(name, type));
	}
	
	
	
	}
	
	
	

