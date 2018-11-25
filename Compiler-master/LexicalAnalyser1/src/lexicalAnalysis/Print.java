package lexicalAnalysis;
import java.util.*;

public class Print extends stmt {

	private List<Item> items;
	
	public Print(List<Item> items) {
		this.items = items;
	}
	
	public void display(String indent) {
		System.out.println(indent + "Print");
		for(int i =0; i<items.size(); i++) {
			items.get(i).display(indent + "  ");
		}
	}
	
	
	
	public Value interpret(SymbolTable t) {
		for (Item it : items) {
			if (it instanceof ExprItem) {
				Expr expr = ((ExprItem) it).getExpr();
				Value value = expr.interpret(t);
				
				System.out.print(value.intValue());
			} else if (it instanceof StringItem) {
				String msg = ((StringItem) it).getMsg();
				System.out.print(msg);
			}
		}
		System.out.println();
		return new VoidValue();
	}

	
}
