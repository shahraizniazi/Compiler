package lexicalAnalysis;

/**
 * Enumeration of the different kinds of tokens in the YASL subset.
 * 
 * @author bhoward
 */
public enum TokenType {
	NUM, // numeric literal
	SEMI, // semicolon (;)
	PLUS, // plus operator (+)
	MINUS, // minus operator (-)
	STAR, // times operator (*)
	ASSIGN,
	VAL,
	BEGIN,
	PRINT,
	END,
	DIV,
	MOD,
	ID,
	PERIOD,
	PROGRAM,
	Not,
	EOF// end-of-file
	
}
