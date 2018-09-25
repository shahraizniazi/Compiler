package lexicalAnalysis;

/**
 * Enumeration of the different kinds of tokens in the YASL subset.
 * 
 *
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
	EOF// end-of-file
	
}
