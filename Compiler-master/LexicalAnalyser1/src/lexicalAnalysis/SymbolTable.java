package lexicalAnalysis;

import java.util.HashMap;
import java.util.Stack;
import java.util.Scanner;

public class SymbolTable {
	
	private Stack<HashMap<String, Value>> table;
	public static Scanner sc = new Scanner(System.in);
	
	public SymbolTable() {
		table = new Stack<>();
		enter();
	}
	
	public void bind(String id, Value value) {
		HashMap<String, Value> t = table.peek();
		if (t.containsKey(id)) {
			System.err.println("Identifier: " + id +  " exists in the same scope");
		} else {
			t.put(id, value);
		}
	}
	
	public Value lookup(String id) {
		for (int i = table.size() - 1; i >= 0; i--) {
			if (table.get(i).containsKey(id)) {
				return table.get(i).get(id);
			}
		}
		
		Value defaultValue = new IntCell(0);
		bind(id, defaultValue);
		return defaultValue;
	}
	

	public void enter() {
		table.push(new HashMap<>());
	}
	
	public void exit() {
		table.pop();
	}
	
	
}
