package lexicalAnalysis;

public abstract class  stmt extends ASTNode {

	public abstract Value interpret(SymbolTable t);

}
