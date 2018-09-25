package lexicalAnalysis;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Parser {
	
	private ArrayList<String> stack;
	private ArrayList<String> StatementTokens;
	
	
	public Parser() {
		stack = new ArrayList<String>();
		StatementTokens = new ArrayList<String>();
	}
	
	
	
	public void ParsingInitial() throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileReader("src/testFile.txt"));
		Token token;
		HashMap<String, String> map = new HashMap<>();
		String variable;
		String value;
		int state=0;
	
		
		//Program Grammar
			token = scanner.next();
			if(token.type==TokenType.EOF) {return;}
			
			if(token.type!=TokenType.PROGRAM) {System.err.println("Error: Invalid Program Declaration, missing Program"); return;}
			token = scanner.next();
			if(token.type!=TokenType.ID) {System.err.println("Error: Invalid Program Declaration, missing Program ID"); return;}
			token = scanner.next();
			if(token.type!=TokenType.SEMI) {System.err.println("Error: Invalid Program Declaration, missing SEMI"); return;}
			
		//Block Grammer => Statement	
			token = scanner.next();
			if(token.type==TokenType.BEGIN) {state =1;}
			if(token.type==TokenType.VAL) {state =2;}
			
			if(token.type==TokenType.EOF) {return;}
			if(state==0) {System.err.println("Error: Invalid Syntax Grammar, missing Statement or "); return;}
			
			
			
			if(state==2) {
			
			while(token.type== TokenType.VAL) {
				
					
						token = scanner.next();
						if(token.type!=TokenType.ID) {System.err.println("Error: Invalid Constant Declaration, missing ID"); return;}
						variable = token.lexeme;
						token= scanner.next();
						if(token.type!=TokenType.ASSIGN) {System.err.println("Error: Invalid Constant Declaration, missing Assignment"); return;}
						token=scanner.next();
						if(token.type!=TokenType.NUM) {System.err.println("Error: Invalid Constant Declaration, missing value"); return;}
						value = token.lexeme;
						map.put(variable, value);
						token=scanner.next();
						if(token.type!=TokenType.SEMI) {System.err.println("Error: Invalid Constant Declaration, missing SEMI"); return;}
						token = scanner.next();
						
						if(token.type==TokenType.VAL) {continue;}
						else if(token.type==TokenType.BEGIN) {state=1; break;}
						else if(token.type==TokenType.EOF) {return;}
						else {
							System.err.println("Error: Invalid Constant Declaration Syntax, missing BEGIN"); return;
						}
						
					}
					
				} 
				
			//SO FAR WE HAVE NOW REACHED BEGIN	
			
			if(token.type==TokenType.BEGIN) {
			
				token= scanner.next();
			
			if(token.type!=TokenType.PRINT) {System.err.println("Error: Invalid Statement Syntax, missing PRINT"); return;}
			if(token.type==TokenType.PRINT) {stack.add("PRINT");}
			
			token = scanner.next();
			
			
			
			//state 11 = ID/NUM
			//state 12= Operators
			//state 5 = SEMI
			//state 6 = Print
			
			
			while(token.type!=TokenType.END ) { 
				
				
				if(token.type==TokenType.EOF) {System.err.println("Not valid, missing END"); return;}
				if(state==5 && token.type!=TokenType.PRINT) {System.err.println("ERROR: Missing PRINT after SEMI"); return;}
				
				
				if(token.type==TokenType.PRINT) { 
					if(state==12) {System.err.println("ERROR: Put a valid ID or a Num before the Print"); return;}
					if(state!=5) {System.err.println("ERROR: Missing SEMI");return;}
					state=6;
					while(stack.size()!=0) {
						StatementTokens.add(stack.get(stack.size()-1));
						System.out.println(stack.get(stack.size()-1));
						stack.remove(stack.size()-1);}
						stack.add("PRINT");}
				
				if(token.type==TokenType.EOF) {System.err.println("Error: Invalid Statement Syntax"); return;}
				
				if(token.type==TokenType.SEMI) { 
					if(state==12) {System.err.println("ERROR: Not a valid print Expression"); return;}
					if(state==6) {System.err.println("ERROR: Not a valid print statement"); return;}
					state=5;
					
					while(stack.size()!=0) {
						StatementTokens.add(stack.get(stack.size()-1));
						System.out.println(stack.get(stack.size()-1));
						stack.remove(stack.size()-1);}}
				
				
				
				if(token.type==TokenType.ID || token.type==TokenType.NUM) {
					if(state==11) {System.err.println("Error: Two Numbers cannot come one after the other"); return;}
					if(token.type==TokenType.NUM) {StatementTokens.add(token.lexeme); System.out.println(token.lexeme); state =11;}
					else {
					if(map.containsKey(token.lexeme)) {StatementTokens.add(map.get(token.lexeme)); System.out.println(map.get(token.lexeme)); state =11;}
					else {System.err.println("Variable " + token.lexeme + " not defined");}}}
				
				
				
				
				
				if(state==12) {System.err.println("Error: Two Operators cannot come one after the other"); return;}
				
				
				if(token.type==TokenType.DIV) {
                    if(stack.get(stack.size()-1)=="*") {
                    	StatementTokens.add("*");
                    	System.out.println("*");
                    	stack.remove(stack.size()-1);
                    }
                    if(stack.get(stack.size()-1)=="MOD") {
                    	StatementTokens.add("MOD");
                    	System.out.println("MOD");
                    	stack.remove(stack.size()-1);
                    }
                    
                    if(stack.get(stack.size()-1)=="DIV") {
                    	StatementTokens.add("DIV");
                    	System.out.println("DIV");
                    	stack.remove(stack.size()-1);
                    }
					
					stack.add("DIV"); state =12;}
				
				if(token.type==TokenType.MOD) {
					
					 if(stack.get(stack.size()-1)=="DIV") {
	                    	StatementTokens.add("DIV");
	                    	System.out.println("DIV");
	                    	stack.remove(stack.size()-1);
	                    }
					
					 if(stack.get(stack.size()-1)=="*") {
	                    	StatementTokens.add("*");
	                    	System.out.println("*");
	                    	stack.remove(stack.size()-1);
	                    }
					
					  if(stack.get(stack.size()-1)=="MOD") {
	                    	StatementTokens.add("MOD");
	                    	System.out.println("MOD");
	                    	stack.remove(stack.size()-1);
	                    }
				stack.add("MOD"); state=12;}
				
				
				if(token.type==TokenType.PLUS) {
					
					 if(stack.get(stack.size()-1)=="*") {
	                    	StatementTokens.add("*");
	                    	System.out.println("*");
	                    	stack.remove(stack.size()-1);
	                    }
					 
					 
					  if(stack.get(stack.size()-1)=="DIV") {
	                    	StatementTokens.add("DIV");
	                    	System.out.println("DIV");
	                    	stack.remove(stack.size()-1);
	                    }
					
					  if(stack.get(stack.size()-1)=="MOD") {
	                    	StatementTokens.add("MOD");
	                    	System.out.println("MOD");
	                    	stack.remove(stack.size()-1);
	                    }
					
					
					  if(stack.get(stack.size()-1)=="-") {
	                    	StatementTokens.add("-");
	                    	System.out.println("-");
	                    	stack.remove(stack.size()-1);
	                    }
					
					
					  if(stack.get(stack.size()-1)=="+") {
	                    	StatementTokens.add("+");
	                    	System.out.println("+");
	                    	stack.remove(stack.size()-1);
	                    }
					stack.add("+"); state=12;}
				if(token.type==TokenType.MINUS) {
					
					 if(stack.get(stack.size()-1)=="DIV") {
	                    	StatementTokens.add("DIV");
	                    	System.out.println("DIV");
	                    	stack.remove(stack.size()-1);
	                    }
					 
					  if(stack.get(stack.size()-1)=="MOD") {
	                    	StatementTokens.add("MOD");
	                    	System.out.println("MOD");
	                    	stack.remove(stack.size()-1);
	                    }
					
					  if(stack.get(stack.size()-1)=="*") {
	                    	StatementTokens.add("*");
	                    	System.out.println("*");
	                    	stack.remove(stack.size()-1);
	                    }
					
					
					if(stack.get(stack.size()-1)=="+") {
	                    	StatementTokens.add("+");
	                    	System.out.println("+");
	                    	stack.remove(stack.size()-1);
	                    }
					
					  if(stack.get(stack.size()-1)=="-") {
	                    	StatementTokens.add("-");
	                    	System.out.println("-");
	                    	stack.remove(stack.size()-1);
	                    }
					
					
					
					stack.add("-"); state=12;}
				if(token.type==TokenType.STAR) {
					
					 if(stack.get(stack.size()-1)=="DIV") {
	                    	StatementTokens.add("DIV");
	                    	System.out.println("DIV");
	                    	stack.remove(stack.size()-1);
	                    }
					
					  if(stack.get(stack.size()-1)=="*") {
	                    	StatementTokens.add("*");
	                    	System.out.println("*");
	                    	stack.remove(stack.size()-1);
	                    }
					
					  if(stack.get(stack.size()-1)=="MOD") {
	                    	StatementTokens.add("MOD");
	                    	System.out.println("MOD");
	                    	stack.remove(stack.size()-1);
	                    }
					
					
					stack.add("*"); state=12;}
			
				
					
			token=scanner.next();
			}
			
			
			
			//END - EMPTY THE STACK
			
			while(stack.size()!=0) {
				StatementTokens.add(stack.get(stack.size()-1));
				System.out.println(stack.get(stack.size()-1));
				stack.remove(stack.size()-1);}
			
			if(state==5) {System.err.println("ERROR: Should have a print statement after SEMI"); return;}
			
			token = scanner.next();
			if(token.type!=TokenType.PERIOD) {System.err.println("ERROR: missing PERIOD after end");}
			
			
			
			}
				
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
		


		
		
	}
	
	



