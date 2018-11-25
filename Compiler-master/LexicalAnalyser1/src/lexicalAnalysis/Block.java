package lexicalAnalysis;

import java.util.*;

public class Block extends ASTNode{

	private  List<ValDecl> vals;
	private  List<VarDecl> vars;
	private  List<funDecl> funs;
	private  stmt body;
	
	public Block(List<ValDecl> vals, List<VarDecl> vars, List<funDecl> funs, stmt body){
		
		this.vals =vals;
		this.vars = vars;
		this.funs = funs;
		this.body = body;
	}
	
	public List<ValDecl> getVals(){
		
		return vals;
	}
	
	public List<VarDecl> getVars(){
		
		return vars;
	}
	
	
	public List<funDecl> getfuns(){
		
		return funs;
	}
	
	public stmt getBody(){
		return body;
	}
	
	
	
	
	
	public void display(String indent) {	
		System.out.println(indent + "Block");
		for(int i =0; i<vals.size();i++) {
			vals.get(i).display(indent + "  ");
		}
		for(int i =0; i<vars.size();i++) {
			vars.get(i).display(indent + "  ");
		}
		
		for(int i =0; i< funs.size();i++) {
			funs.get(i).display(indent + "  ");
			
		}
		body.display(indent + "  ");
		
	}
	
	
	public Value interpret(SymbolTable t) {
		
		for (ValDecl d : vals) {
			d.interpret(t);
		}
		for (VarDecl d : vars) {
			d.interpret(t);
		}
		for (funDecl d : funs) {
			d.interpret(t);
		}
		
		return body.interpret(t);
	}

	
}
