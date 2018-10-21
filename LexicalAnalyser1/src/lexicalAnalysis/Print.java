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
	
}
