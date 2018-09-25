package lexicalAnalysis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main class for Project 1 -- Scanner for a Subset of YASL. Scans
 * tokens from standard input and prints the token stream to standard output.
 * 
 * 
 */
public class mainProject {
	public static void main(String[] args) throws IOException {
		
		Parser pr = new Parser();
		pr.ParsingInitial();
		
		/*
		Scanner scanner = new Scanner(new FileReader("src/testFile.txt"));

		Token token;
	
		
		do {
			token = scanner.next();
			System.out.println(token);
		} while (token.type != TokenType.EOF);

		scanner.close();
		*/
	}
}
