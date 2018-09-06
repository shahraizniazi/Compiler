package lexicalAnalysis;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

/**
 * A Lexical Analyzer for a subset of YASL. Uses a (Mealy) state machine to
 * extract the next available token from the input each time next() is called.
 * Input comes from a Reader, which will generally be a BufferedReader wrapped
 * around a FileReader or InputStreamReader (though for testing it may also be
 * simply a StringReader).
 * 
 * @author bhoward
 */
public class Scanner {
	/**
	 * Construct the Scanner ready to read tokens from the given Reader.
	 * 
	 * @param in
	 */
	public Scanner(Reader in) {
		source = new Source(in);
	}

	/**
	 * Extract the next available token. When the input is exhausted, it will
	 * return an EOF token on all future calls.
	 * 
	 * @return the next Token object
	 */
	public Token next() {
		
		int state=0;
		int column=0;
		TokenType temp1= TokenType.ASSIGN;
		StringBuilder temp = new StringBuilder();
	
		
		//if EOF, return
		if (source.atEOF) {return (new Token(source.line+1, 1, TokenType.EOF,null));}
		//ignore initial whitespaces between every word and the beginning
		while(Character.isWhitespace(source.current)) {source.advance();}
		
		
		
		
		//check for // coment format
		if(source.current=='/') {source.advance();if(source.current=='/') {
		while(source.current!='\n' && source.current!='\n') {source.advance();}
		return (new Token(source.line, source.column, TokenType.Not,null));}
		//after one / look for the next *
		if(source.current=='*') {state=10; source.advance();}}
	
				
			
		
		// column initialized here
		column=source.column;
		
		
		if(state==10) {
			while(state!=11) {
					if(source.atEOF) {System.err.println("Error: Comment ont closed on line: "+ source.line);
					return (new Token(source.line, source.column, TokenType.Not,null));}
					if(source.current=='*') {state=9;source.advance();}
					if(state==9 && source.current=='/') {state=11; source.advance();return (new Token(source.line, source.column, TokenType.Not,null));}
					
					source.advance();
					state=10;}}
		
		
		//Check for punctuation and operators 
		if(source.current == '+' || source.current=='-' || source.current=='*' || source.current=='=' || source.current=='.' || source.current==';') {
			state=1;
			temp.append(source.current);
			source.advance();
			
		}
		
		//check for puntuation and Operators printing
		if (state==1)
		{
		
			if(temp.toString().charAt(0) == ';' ) {return (new Token(source.line, column, TokenType.SEMI,null));}		
			if(temp.toString().charAt(0) == '.' ) {return (new Token(source.line, column, TokenType.PERIOD,null));}	
			if(temp.toString().charAt(0) == '+' ) {return (new Token(source.line, column, TokenType.PLUS,null));}	
			if(temp.toString().charAt(0) == '-' ) {return (new Token(source.line, column, TokenType.MINUS,null));}	
			if(temp.toString().charAt(0) == '*' ) {return (new Token(source.line, column, TokenType.STAR,null));}	
			if(temp.toString().charAt(0) == '=' ) {return (new Token(source.line, column, TokenType.ASSIGN,null));}	
		
		}
		
		
		
		
		//check for digits --- NUM
		if(Character.isDigit(source.current) && state!=1) {
			state=5;
			temp.append(source.current);
			source.advance();
			while(Character.isDigit(source.current)) {temp.append(source.current); source.advance();}
			
		}
		
		
		//NUM return and conditions
		if(state==5)
		{
			if(temp.length()>1) {if(temp.charAt(0)=='0') {System.err.println(("ERROR: Not a valid [NUM] on line: " + source.line + " and column: " +column));}}
			return (new Token(source.line, column, TokenType.NUM,temp.toString()));
				
		}
		
		
		
		//Check for punctuation and identifier combined. One after the other
		
		if(Character.isLetter(source.current)) 
		{
			temp.append(source.current);source.advance();
			while(Character.isLetter(source.current)) {temp.append(source.current);source.advance();}
			if(isKeyword(temp.toString())) 
				{
				String[] keyword = {"program", "val", "begin","print","end","div","mod"};	
				for(int i =0; i< keyword.length;i++) 
				{
					if(temp.toString().equals(keyword[i]))
					{	
						if(i==0) {temp1=TokenType.PROGRAM;}if(i==1) {temp1=TokenType.VAL;}if(i==2) {temp1=TokenType.BEGIN;}
						if(i==3) {temp1=TokenType.PRINT;}if(i==4) {temp1=TokenType.END;}if(i==5) {temp1=TokenType.DIV;}if(i==6) {temp1=TokenType.MOD;}	
					}
				}
				return (new Token(source.line, column, temp1, null));
				}
			
			else {while(Character.isDigit(source.current)) {temp.append(source.current);source.advance();}}
				
			return (new Token(source.line, column, TokenType.ID,temp.toString()));
			
		}
		
		
		
		
		
		
		System.err.println("ERROR: illegal Character on line: " + source.line + " coulmn: " + column);
		source.advance();
		return (new Token(source.line+1, 1, TokenType.Not,null));
	} //end of next()
		
	
		
	
	
	private boolean isKeyword(String temp) {
		return (temp.equals("program") || temp.equals("val") || temp.equals("begin") || temp.equals("print") || temp.equals("end") || temp.equals("div") || temp.equals("mod"));
	}
	
	


	/**
	 * Close the underlying Reader.
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		source.close();
	}

	private Source source;
}
