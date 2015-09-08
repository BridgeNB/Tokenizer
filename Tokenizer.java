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
		// Split input string
		String[] tokenCan = input.split("");
		
		// Parse alphabet inside takenCan
		for(int k = 0; k < tokenCan.length; k++) {
			if (tokenCan[k].matches("[A-Za-z]"))
				tokenCan[k] = "";
		}
		
		// Clean empty strings in arraylist
		candidates = cleanEmptyString(tokenCan); 
		
		// Parse integer elements of input
		String[] preTokenCan = new String[candidates.size()]; 
		preTokenCan = (String[]) candidates.toArray(preTokenCan);
		String[] aftTokenCan = parseInteger(preTokenCan);

		// Clean empty strings in arraylist
		candidates = cleanEmptyString(aftTokenCan); 
		
		// Parse float elements of input
		String[] IntTokenCan = new String[candidates.size()]; 
		IntTokenCan = (String[]) candidates.toArray(IntTokenCan);
		String[] FloTokenCan = parseFloat(IntTokenCan);
		
		// Clean empty strings in arraylist
		candidates = cleanEmptyString(FloTokenCan); 
		
		// test final candiates array
		for (String t : candidates) {
			
			if (t.matches("\\d+")) {
				// Parse integer number
				int integer_token = Integer.parseInt(t);
				newElement = new Token(11, integer_token);
				tokens.add(newElement);
			} else if (t.matches("\\d*\\.\\d+")) {
				// Parse decimal number
				float float_token = Float.parseFloat(t);
				newElement = new Token(12, float_token);
				tokens.add(newElement);
			} else {
				// Parse string value
				newElement = symbolAnalysis(t);
				// Only add vaild symbol
				if (newElement != null)
					tokens.add(newElement);
			}
		}
		
		return tokens;
	}
	
	public String read_input(Scanner in){
		/*TODO: Read input until a '?' is found */
		int questionMarkLoc = -1;
		// indicater illustrates whether a ? has been detected.
		int indicater = 0;
		// temporary input string
		String firstinput;
		// have not taken care of two line cases	
		do {
			firstinput = in.nextLine();
			// find ? location
			for(int stringCounter = 0; stringCounter < firstinput.length(); stringCounter++) {
				if(firstinput.charAt(stringCounter) == '?') {
					questionMarkLoc = stringCounter;
					indicater = 1;
					break;
				}
			}
			
			// Concatenate two strings if necessary
			if (questionMarkLoc == -1) {
				input += firstinput;
			} else {
				input += firstinput.substring(0, questionMarkLoc + 1);
			}
		// quit when a question mark notation has been found
		} while (indicater == 0);
				
		return input;
	}
	
	public void print_tokens(ArrayList<Token> tokens) {
		/*TODO: Print all the tokens before and including the '?' token
		 *      Print tokens from list in the following way, "(token,tokenValue)"
		 * */
		for (int i = 0; i < tokens.size(); i++) {
			// Print getToken value
			System.out.print("(" + tokens.get(i).getToken() + ",");
			// Print Token value
			if(tokens.get(i).getType() == 'i') {
				// Print integer token value
				System.out.print(tokens.get(i).getValue_i());
			}
			else if(tokens.get(i).getType() == 'f') {
				// Print float token value
				System.out.print(tokens.get(i).getValue_f());
			} else {
				// Print string token value
				System.out.print(tokens.get(i).getValue_s());
			}
			System.out.print(")");
		}
	}
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		Tokenizer p0 = new Tokenizer();
		input = p0.read_input(in);
		tokens = p0.get_tokens(input);
		p0.print_tokens(tokens);
	}
	
	// Parse symbol element in the array and return token
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
	
	// Clean empty string in Arraylist
	public ArrayList<String> cleanEmptyString (String[] dirtyString) {
		ArrayList<String> nonEmpStrings = new ArrayList<String>();
		
		// Remove empty string from the candidate string array
		for (String s : dirtyString) {
			// Replace multiple spaces by empty string
			s.replaceAll("[\\t\\n\\r\\s]", "");
			// add nonempty string to new arraylist
			if (!s.matches(" ") && !s.matches(""))
				nonEmpStrings.add(s);
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
					unparsedArray[j].matches("\\.") && unparsedArray[j + 1].matches("\\d+")) {
				unparsedArray[j + 1] = unparsedArray[j - 1] + unparsedArray[j] + unparsedArray[j + 1];
				unparsedArray[j] = "";
				unparsedArray[j - 1] = "";
			}
			
		}
		
		return unparsedArray;
	}

}

