package lexicalAnalysis;

public abstract class Expr extends ASTNode {

	public abstract Value interpret(SymbolTable table);

}
