package lexicalAnalysis;

public class BinOp extends Expr {
	private Expr  left;
	private Op2 op;
	private Expr right;
	
	public BinOp(Expr left, Op2 op, Expr right) {
		this.left = left;
		this.op = op;
		this.right = right;
	}
	
	
	public Expr getLeft() {
		return left;
	}

	public Op2 getOp() {
		return op;
	}

	public Expr getRight() {
		return right;
	}

	public void display(String indent) {
		System.out.println(indent + "BinnOp " + op);
		left.display(indent + "  ");
		right.display(indent + "  ");
	}
	
	
	public Value interpret(SymbolTable t) {
		Value lhs = left.interpret(t);
		Value rhs = right.interpret(t);
				
		int lhsIntVal = 0;
		int rhsIntVal = 0;
		
		
		if (lhs instanceof IntValue) {
			lhsIntVal = lhs.intValue();
		}
		if (rhs instanceof IntValue) {
			rhsIntVal = rhs.intValue();

		}
		
		switch(this.op) {
		case And:
			if (lhs.boolValue())
				return rhs;
			else
				return lhs;
		case Or:
			if (lhs.boolValue())
				return lhs;
			else
				return rhs;
		case EQ:
			return new BoolValue(lhsIntVal == rhsIntVal);
		case NE:
			return new BoolValue(lhsIntVal != rhsIntVal);
		case LE:
			return new BoolValue(lhsIntVal <= rhsIntVal);
		case LT:
			return new BoolValue(lhsIntVal < rhsIntVal);
		case GE:
			return new BoolValue(lhsIntVal >= rhsIntVal);
		case GT:
			return new BoolValue(lhsIntVal > rhsIntVal);
		case Plus:
			return new IntValue(lhsIntVal + rhsIntVal);
		case Minus:
			return new IntValue(lhsIntVal - rhsIntVal);
		case Times:
			return new IntValue(lhsIntVal * rhsIntVal);
		case Div:
			return new IntValue(lhsIntVal / rhsIntVal);
		case Mod:
			return new IntValue(lhsIntVal % rhsIntVal);			
		default:
			return null; 
		}
	}

	
	
	
	
}
