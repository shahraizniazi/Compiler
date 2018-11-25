package lexicalAnalysis;

import java.util.List;



public class FunValue extends Value{
	
	private List<Param> ps;
	private Type type;
	private Block block;
	
	public List<Param> getParams() { return ps; }
	public Type getType() { return type; }
	public Block getBlock() { return block; }

	public FunValue(List<Param> ps, Type type, Block block) {
		this.ps = ps;
		this.type = type;
		this.block = block;
	}
	
	public String toString() {
		String ans = "FUNVAL\n";
		ans += "Parameters: " + ps.toString() + "\n";
		ans += "Type: " + type + "\n";
		return ans;
	}
	
	
	public int intValue() {
		System.err.println("Cannot get int from function value");
		System.exit(0);
		return 0;
	}

	public boolean boolValue() {
		System.err.println("Cannot get boolean from function value");
		System.exit(0);
		return false;
	}
}