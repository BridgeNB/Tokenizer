import java.util.ArrayList;
import java.util.Scanner;

public class Tokenizer{
	static ArrayList<Token> tokens = new ArrayList<Token>();
	static String input;
	
	public ArrayList<Token> get_tokens(String input) {
		/*TODO: Split the input into the seperate tokens */
		Token newElement;
		// Convert input string to arraylist for further manipulation
		ArrayList<String> candidates = new ArrayList<String>();
		
//		String[] tokenNum = input.split("[\\p{Punct}\\s]+");
		String[] tokenCan = input.split("");
		
		// Cancanate integer number
		for (int i = 0; i < tokenCan.length - 1; i++) {
			
			if (tokenCan[i].matches("\\d+") && tokenCan[i + 1].matches("\\d+")) {
				tokenCan[i+1] = tokenCan[i] + tokenCan[i+1];
				tokenCan[i] ="";
			}
			
			System.out.print("the " + i + " element is " + tokenCan[i] +"\n");
		}
		
		// Cancanate float number
		for (int j = 1; j < tokenCan.length - 1; j++) {
			
			if (tokenCan[j - 1].matches("\\d+") &&
					tokenCan[j].matches(".") && tokenCan[j + 1].matches("\\d+")) {
				tokenCan[j + 1] = tokenCan[j - 1] + tokenCan[j] + tokenCan[j + 1];
				tokenCan[j - 1] = "";
				tokenCan[j] = "";
			}
			
			System.out.print("the " + j + " element is " + tokenCan[j] +"\n");
		}
		
		// Remove empty string from the candidate string array
		for (String s : tokenCan) {
			if (!s.equals("")) {
				candidates.add(s);
			}			
		}
		
		// test final candiates array
		for (String t : candidates)
			System.out.println("xxx " + t);
		// further parse needed
		
		
		
//		newElement = new Token(11, tokenCan[j]);
//		tokens.add(newElement);
			
//		// Obtain seperated token digits
//		for(int i = 0; i < tokenCan.length; i++) {
//			System.out.print("the " + i + " element is " + tokenCan[i] +"\n");
//			candidates.add(tokenCan[i]);
//			
//			newElement = new Token(11, tokenCan[i]);
//			tokens.add(newElement);
//		}
		
		Token t1 = symbolAnalysis("+");
		//System.out.print("Token arraylist size: " + tokens.size());

		return tokens;
	}
	
	public String read_input(Scanner in){
		/*TODO: Read input until a '?' is found */
		int questionMarkLoc = 0;
		// have not taken care of two line cases	
		input = in.nextLine();
		System.out.println("Input is " + input);
		
		System.out.println("Length of string" + input.length());
		
		for(int stringCounter = 0; stringCounter < input.length(); stringCounter++) {
			if(input.charAt(stringCounter) == '?') {
				questionMarkLoc = stringCounter;
				break;
			}
		}
		
		String newInput = input.substring(0, questionMarkLoc + 1);
		
		System.out.println("quation mark location + " + questionMarkLoc);
		System.out.println("New String is " + newInput);
		
		return newInput;
	}
	
	public void print_tokens(ArrayList<Token> tokens) {
		/*TODO: Print all the tokens before and including the '?' token
		 *      Print tokens from list in the following way, "(token,tokenValue)"
		 * */
		for (int i = 0; i < tokens.size(); i++) {
			tokens.get(i).print();
		}
			
		
	}
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		Tokenizer p0 = new Tokenizer();
		input = p0.read_input(in);
		tokens = p0.get_tokens(input);
		p0.print_tokens(tokens);
	}
	
	public Token symbolAnalysis (String symbol) {
		
		Token s = null;
		
		if(symbol.compareTo("?") == 0) 
			s = new Token(20,0);
		if(symbol.compareTo("(") == 0)
			s = new Token(21,0);
		if(symbol.compareTo(")") == 0) 
			s = new Token(22,0);
		if(symbol.compareTo("+") == 0)
			s = new Token(23,0);
		if(symbol.compareTo("-") == 0) 
			s = new Token(24,0);
		if(symbol.compareTo("*") == 0)
			s = new Token(25,0);
		if(symbol.compareTo("/") == 0) 
			s = new Token(25,0);
		if(symbol.compareTo(".") == 0)
			s = new Token(27,0);
		if(symbol.compareTo(";") == 0) 
			s = new Token(28,0);
		if(symbol.compareTo("=") == 0)
			s = new Token(29,0);
		
		return s;
	}
}

