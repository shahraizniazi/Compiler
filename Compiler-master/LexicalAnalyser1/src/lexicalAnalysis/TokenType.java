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
	VAR,
	INT,
	BOOL,
	VOID,
	FUN,
	IF,
	THEN,
	ELSE,
	WHILE,
	DO,
	INPUT,
	AND,
	OR,
	NOT,
	TRUE,
	FALSE,
	COLON,
	LPAREN,
	RPAREN,
	COMMA,
	EQUAL,
	NOTEQUAL,
	LESSEQUAL,
	GREATEREQUAL,
	LESS,
	GREATER,
	STRING,
	LET,
	EOF// end-of-file
	
}
