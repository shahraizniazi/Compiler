package lexicalAnalysis;
import java.util.*;

public class Call extends Expr{


	private String id;
	private List<Expr> args;
	
	public Call(String id, List<Expr> args) {
		
		this.id = id;
		this.args=args;
	}
	
	
	public void display(String indent) {
		System.out.println(indent + "Call " + id);
		
		for(int i =0; i<args.size();i++) {
			args.get(i).display(indent+ "  ");
		}
		
	}


	@Override
	public Value interpret(SymbolTable table) {
		
		FunValue funV = (FunValue) table.lookup(id);
		
		List<Param> params = funV.getParams(); 
		Block block = funV.getBlock();
		List<Value> as = new ArrayList<Value>();
		
		for (Expr e : args) {
			as.add(e.interpret(table));
		}
		
		table.enter();
		Value result = call(params, block, as, table);
		table.exit();
		
		return result;
		
		
	}


	private Value call(List<Param> params, Block block, List<Value> as, SymbolTable table) {
	
		if(params == null) {
			return block.interpret(table);
		}
		
		if (params.size() != as.size()) {
			System.err.println("Invalid number of arguments to the function " + id);
			System.exit(0);
		}
		
		else {
			for (int i = 0; i < params.size(); i++) {
				table.bind(params.get(i).getId(), as.get(i));
			}
			return block.interpret(table);
		}
		return null;//unreachable
		
		
	}
	
	
}
