import java.util.ArrayList;
import java.util.Scanner;

public class Project0{
	static ArrayList<Token> tokens = new ArrayList<Token>();
	static String input;
	
	public ArrayList<Token> get_tokens(String input) {
		/*TODO: Split the input into the seperate tokens */
		
	}
	
	public String read_input(Scanner in){
		/*TODO: Read input until a '?' is found */
		
	}
	
	public void print_tokens(ArrayList<Token> tokens) {
		/*TODO: Print all the tokens before and including the '?' token
		 *      Print tokens from list in the following way, "(token,tokenValue)"
		 * */
	}
	
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		Project0 p0 = new Project0();
		input = p0.read_input(in);
		tokens = p0.get_tokens(input);
		p0.print_tokens(tokens);
	}
}

