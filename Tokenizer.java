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
		ArrayList<String> cd_integer = new ArrayList<String>();
		
		
//		String[] tokenNum = input.split("[\\p{Punct}\\s]+");
		String[] tokenCan = input.split("");
		
		// Parse integer elements of input
		tokenCan = parseInteger (tokenCan);
		// Clean empty strings in arraylist
		candidates = cleanEmptyString(tokenCan); 
		// Parse float elements of input
		String[] IntTokenCan = new String[candidates.size()]; 
		IntTokenCan = (String[]) candidates.toArray(IntTokenCan);
		String[] FloTokenCan = parseFloat(IntTokenCan);
		// Clean empty strings in arraylist
		candidates = cleanEmptyString(FloTokenCan); 
		
		
		for (String s1: candidates) {
			s1 = s1.replaceAll("\\s+","");
			if (!s1.equals("")) {
				cd_integer.add(s1);
			}
			System.out.println("float round " + s1);
		}
		
		System.out.println("candidates size is" + candidates.size());
		
		
		// test final candiates array
		for (String t : candidates) {
			System.out.println("xxx " + t);
			
			if (t.matches("\\d+")) {
				// Parse integer number
				int integer_token = Integer.parseInt(t);
				System.out.println("It is a integer " + integer_token);
				newElement = new Token(11, integer_token);
				tokens.add(newElement);
			} else if (t.matches("\\d*\\.\\d+")) {
				// Parse decimal number
				float float_token = Float.parseFloat(t);
				System.out.println("It is a float " + float_token);
				newElement = new Token(12, float_token);
				tokens.add(newElement);
			} else {
				System.out.println("It is a symbol " + t);
				newElement = symbolAnalysis(t);
				tokens.add(newElement);
			}
		}
		
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
			s = new Token(20,symbol);
		if(symbol.compareTo("(") == 0)
			s = new Token(21,symbol);
		if(symbol.compareTo(")") == 0) 
			s = new Token(22,symbol);
		if(symbol.compareTo("+") == 0)
			s = new Token(23,symbol);
		if(symbol.compareTo("-") == 0) 
			s = new Token(24,symbol);
		if(symbol.compareTo("*") == 0)
			s = new Token(25,symbol);
		if(symbol.compareTo("/") == 0) 
			s = new Token(25,symbol);
		if(symbol.compareTo(".") == 0)
			s = new Token(27,symbol);
		if(symbol.compareTo(";") == 0) 
			s = new Token(28,symbol);
		if(symbol.compareTo("=") == 0)
			s = new Token(29,symbol);
		
		return s;
	}
	
	public ArrayList<String> cleanEmptyString (String[] dirtyString) {
		ArrayList<String> nonEmpStrings = new ArrayList<String>();
		
		// Remove empty string from the candidate string array
		for (String s : dirtyString) {
			// Replace multiple spaces by empty string
			s = s.replaceAll("\\s+", "");
			if (!s.equals("")) {
				nonEmpStrings.add(s);
			}			
		}
		
		return nonEmpStrings;
	}
	
	// Parse integer element in the array
	public String[] parseInteger (String[] unparsedArray) {
		for (int i = 0; i < unparsedArray.length - 1; i++) {
			
			if (unparsedArray[i].matches("\\d+") && unparsedArray[i + 1].matches("\\d+")) {
				unparsedArray[i+1] = unparsedArray[i] + unparsedArray[i+1];
				unparsedArray[i] ="";
			}

		}	
		return unparsedArray;
	}
	
	// Parse float element in the array
	public String[] parseFloat (String[] unparsedArray) {

		for (int j = 1; j < unparsedArray.length - 1; j++) {

			if (unparsedArray[j - 1].matches("\\d+") &&
					unparsedArray[j].matches(".") && unparsedArray[j + 1].matches("\\d+")) {
				unparsedArray[j + 1] = unparsedArray[j - 1] + unparsedArray[j] + unparsedArray[j + 1];
				unparsedArray[j] = "";
				unparsedArray[j - 1] = "";
			}
		}
		
		return unparsedArray;
	}

}

